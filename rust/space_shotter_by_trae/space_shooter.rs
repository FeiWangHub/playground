use std::io::{self, Write};
use std::time::{Duration, Instant};
use std::collections::VecDeque;
use std::thread;

// 游戏配置常量
const GAME_WIDTH: usize = 40;
const GAME_HEIGHT: usize = 20;
const PLAYER_CHAR: char = '^';
const ENEMY_CHAR: char = 'V';
const BULLET_CHAR: char = '|';
const EMPTY_CHAR: char = ' ';
const BORDER_CHAR: char = '#';

// 位置结构体
#[derive(Clone, Copy, PartialEq)]
struct Position {
    x: usize,
    y: usize,
}

// 游戏实体
struct Player {
    pos: Position,
}

struct Enemy {
    pos: Position,
}

struct Bullet {
    pos: Position,
}

// 游戏状态
struct Game {
    player: Player,
    enemies: Vec<Enemy>,
    bullets: Vec<Bullet>,
    score: u32,
    game_over: bool,
    last_enemy_spawn: Instant,
    last_bullet_time: Instant,
}

impl Game {
    fn new() -> Self {
        Game {
            player: Player {
                pos: Position {
                    x: GAME_WIDTH / 2,
                    y: GAME_HEIGHT - 2,
                },
            },
            enemies: Vec::new(),
            bullets: Vec::new(),
            score: 0,
            game_over: false,
            last_enemy_spawn: Instant::now(),
            last_bullet_time: Instant::now(),
        }
    }

    fn update(&mut self) {
        // 移动子弹
        self.bullets.retain_mut(|bullet| {
            if bullet.pos.y > 0 {
                bullet.pos.y -= 1;
                true
            } else {
                false
            }
        });

        // 移动敌机
        self.enemies.retain_mut(|enemy| {
            enemy.pos.y += 1;
            if enemy.pos.y >= GAME_HEIGHT - 1 {
                false
            } else {
                true
            }
        });

        // 生成新敌机
        if self.last_enemy_spawn.elapsed() > Duration::from_millis(1000) {
            let enemy_x = fastrand::usize(1..GAME_WIDTH - 1);
            self.enemies.push(Enemy {
                pos: Position { x: enemy_x, y: 1 },
            });
            self.last_enemy_spawn = Instant::now();
        }

        // 检查子弹与敌机碰撞
        let mut bullets_to_remove = Vec::new();
        let mut enemies_to_remove = Vec::new();

        for (bullet_idx, bullet) in self.bullets.iter().enumerate() {
            for (enemy_idx, enemy) in self.enemies.iter().enumerate() {
                if bullet.pos.x == enemy.pos.x && bullet.pos.y == enemy.pos.y {
                    bullets_to_remove.push(bullet_idx);
                    enemies_to_remove.push(enemy_idx);
                    self.score += 10;
                }
            }
        }

        // 移除被击中的子弹和敌机
        bullets_to_remove.sort_by(|a, b| b.cmp(a));
        enemies_to_remove.sort_by(|a, b| b.cmp(a));
        
        for &idx in &bullets_to_remove {
            if idx < self.bullets.len() {
                self.bullets.remove(idx);
            }
        }
        
        for &idx in &enemies_to_remove {
            if idx < self.enemies.len() {
                self.enemies.remove(idx);
            }
        }

        // 检查玩家与敌机碰撞
        for enemy in &self.enemies {
            if enemy.pos.x == self.player.pos.x && enemy.pos.y == self.player.pos.y {
                self.game_over = true;
                break;
            }
        }
    }

    fn move_player_left(&mut self) {
        if self.player.pos.x > 1 {
            self.player.pos.x -= 1;
        }
    }

    fn move_player_right(&mut self) {
        if self.player.pos.x < GAME_WIDTH - 2 {
            self.player.pos.x += 1;
        }
    }

    fn shoot(&mut self) {
        if self.last_bullet_time.elapsed() > Duration::from_millis(200) {
            self.bullets.push(Bullet {
                pos: Position {
                    x: self.player.pos.x,
                    y: self.player.pos.y - 1,
                },
            });
            self.last_bullet_time = Instant::now();
        }
    }

    fn render(&self) {
        // 清屏
        print!("\x1B[2J\x1B[1;1H");
        
        // 创建游戏画面
        let mut screen = vec![vec![EMPTY_CHAR; GAME_WIDTH]; GAME_HEIGHT];
        
        // 绘制边框
        for x in 0..GAME_WIDTH {
            screen[0][x] = BORDER_CHAR;
            screen[GAME_HEIGHT - 1][x] = BORDER_CHAR;
        }
        for y in 0..GAME_HEIGHT {
            screen[y][0] = BORDER_CHAR;
            screen[y][GAME_WIDTH - 1] = BORDER_CHAR;
        }
        
        // 绘制玩家
        screen[self.player.pos.y][self.player.pos.x] = PLAYER_CHAR;
        
        // 绘制敌机
        for enemy in &self.enemies {
            screen[enemy.pos.y][enemy.pos.x] = ENEMY_CHAR;
        }
        
        // 绘制子弹
        for bullet in &self.bullets {
            screen[bullet.pos.y][bullet.pos.x] = BULLET_CHAR;
        }
        
        // 输出画面
        for row in &screen {
            for &ch in row {
                print!("{}", ch);
            }
            println!();
        }
        
        // 显示得分和控制说明
        println!("得分: {}", self.score);
        println!("控制: A/D 移动, W 射击, Q 退出");
        
        if self.game_over {
            println!("\n游戏结束! 最终得分: {}", self.score);
            println!("按任意键退出...");
        }
        
        io::stdout().flush().unwrap();
    }
}

// 非阻塞输入检查
fn check_input() -> Option<char> {
    use std::sync::mpsc;
    use std::thread;
    use std::time::Duration;
    
    let (tx, rx) = mpsc::channel();
    
    thread::spawn(move || {
        let mut input = String::new();
        if io::stdin().read_line(&mut input).is_ok() {
            if let Some(ch) = input.chars().next() {
                let _ = tx.send(ch.to_ascii_lowercase());
            }
        }
    });
    
    match rx.recv_timeout(Duration::from_millis(50)) {
        Ok(ch) => Some(ch),
        Err(_) => None,
    }
}

fn main() {
    println!("欢迎来到Rust打飞机游戏!");
    println!("按回车开始游戏...");
    
    let mut input = String::new();
    io::stdin().read_line(&mut input).unwrap();
    
    let mut game = Game::new();
    let mut last_update = Instant::now();
    
    // 游戏主循环
    loop {
        // 处理输入
        if let Some(ch) = check_input() {
            match ch {
                'a' => game.move_player_left(),
                'd' => game.move_player_right(),
                'w' => game.shoot(),
                'q' => break,
                _ => {}
            }
        }
        
        // 更新游戏状态
        if last_update.elapsed() > Duration::from_millis(100) {
            game.update();
            last_update = Instant::now();
        }
        
        // 渲染游戏
        game.render();
        
        // 检查游戏结束
        if game.game_over {
            let mut input = String::new();
            io::stdin().read_line(&mut input).unwrap();
            break;
        }
        
        // 控制帧率
        thread::sleep(Duration::from_millis(50));
    }
    
    println!("感谢游玩!");
}