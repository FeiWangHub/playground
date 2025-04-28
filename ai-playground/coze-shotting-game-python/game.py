import pygame
import random
import sys

# 初始化pygame
pygame.init()

# 游戏窗口设置
WIDTH, HEIGHT = 800, 600
screen = pygame.display.set_mode((WIDTH, HEIGHT))
pygame.display.set_caption("飞机射击蜜蜂")

# 颜色定义
WHITE = (255, 255, 255)
BLACK = (0, 0, 0)
RED = (255, 0, 0)
BLUE = (0, 0, 255)
YELLOW = (255, 255, 0)

# 飞机设置
plane_width, plane_height = 50, 30
plane_x = WIDTH // 2 - plane_width // 2
plane_y = HEIGHT - plane_height - 20
plane_speed = 5

# 子弹设置
bullets = []
bullet_width, bullet_height = 5, 15
bullet_speed = 7

# 蜜蜂设置
bees = []
bee_width, bee_height = 40, 40
bee_speed = 3
bee_spawn_rate = 60  # 帧数

# 分数
score = 0
font = pygame.font.SysFont(None, 36)

# 游戏主循环
clock = pygame.time.Clock()
spawn_counter = 0
running = True

while running:
    screen.fill(BLACK)
    
    # 事件处理
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            running = False
        elif event.type == pygame.KEYDOWN:
            if event.key == pygame.K_SPACE:
                # 发射子弹
                bullets.append([plane_x + plane_width // 2 - bullet_width // 2, plane_y])
    
    # 飞机移动
    keys = pygame.key.get_pressed()
    if keys[pygame.K_LEFT] and plane_x > 0:
        plane_x -= plane_speed
    if keys[pygame.K_RIGHT] and plane_x < WIDTH - plane_width:
        plane_x += plane_speed
    
    # 生成蜜蜂
    spawn_counter += 1
    if spawn_counter >= bee_spawn_rate:
        bees.append([random.randint(0, WIDTH - bee_width), 0])
        spawn_counter = 0
    
    # 更新子弹位置
    for bullet in bullets[:]:
        bullet[1] -= bullet_speed
        if bullet[1] < 0:
            bullets.remove(bullet)
    
    # 更新蜜蜂位置
    for bee in bees[:]:
        bee[1] += bee_speed
        if bee[1] > HEIGHT:
            running = False  # 游戏结束
    
    # 碰撞检测
    for bullet in bullets[:]:
        for bee in bees[:]:
            if (bullet[0] < bee[0] + bee_width and
                bullet[0] + bullet_width > bee[0] and
                bullet[1] < bee[1] + bee_height and
                bullet[1] + bullet_height > bee[1]):
                bullets.remove(bullet)
                bees.remove(bee)
                score += 1
                break
    
    # 绘制飞机
    pygame.draw.rect(screen, BLUE, (plane_x, plane_y, plane_width, plane_height))
    
    # 绘制子弹
    for bullet in bullets:
        pygame.draw.rect(screen, YELLOW, (bullet[0], bullet[1], bullet_width, bullet_height))
    
    # 绘制蜜蜂
    for bee in bees:
        pygame.draw.rect(screen, RED, (bee[0], bee[1], bee_width, bee_height))
    
    # 显示分数
    score_text = font.render(f"分数: {score}", True, WHITE)
    screen.blit(score_text, (10, 10))
    
    pygame.display.flip()
    clock.tick(60)

# 游戏结束显示
game_over_text = font.render(f"游戏结束! 最终分数: {score}", True, WHITE)
screen.blit(game_over_text, (WIDTH // 2 - 150, HEIGHT // 2))
pygame.display.flip()

# 等待用户关闭窗口
while True:
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            pygame.quit()
            sys.exit()
    clock.tick(60)