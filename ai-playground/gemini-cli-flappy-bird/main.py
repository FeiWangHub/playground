import pygame
import random
import sys

# --- Constants ---
SCREEN_WIDTH = 800
SCREEN_HEIGHT = 600
BIRD_WIDTH = 40
BIRD_HEIGHT = 30
PIPE_WIDTH = 70
PIPE_GAP = 150
GRAVITY = 0.25
JUMP_STRENGTH = -7
PIPE_SPEED = 3

# --- Colors ---
WHITE = (255, 255, 255)
BLACK = (0, 0, 0)
SKY_BLUE = (135, 206, 235)
GREEN = (0, 255, 0)

# --- Game Setup ---
pygame.init()
screen = pygame.display.set_mode((SCREEN_WIDTH, SCREEN_HEIGHT))
pygame.display.set_caption("Flappy Bird")
clock = pygame.time.Clock()
font = pygame.font.Font(None, 50)

# --- Assets ---
def create_bird_image():
    bird_image = pygame.Surface((BIRD_WIDTH, BIRD_HEIGHT), pygame.SRCALPHA)
    pygame.draw.ellipse(bird_image, (255, 255, 0), (0, 0, BIRD_WIDTH, BIRD_HEIGHT)) # Yellow body
    pygame.draw.ellipse(bird_image, WHITE, (BIRD_WIDTH // 4, BIRD_HEIGHT // 4, 10, 10)) # Eye
    pygame.draw.ellipse(bird_image, BLACK, (BIRD_WIDTH // 4 + 2, BIRD_HEIGHT // 4 + 2, 5, 5)) # Pupil
    pygame.draw.polygon(bird_image, (255, 165, 0), [(BIRD_WIDTH - 10, BIRD_HEIGHT // 2), (BIRD_WIDTH, BIRD_HEIGHT // 2 - 5), (BIRD_WIDTH, BIRD_HEIGHT // 2 + 5)]) # Beak
    return bird_image

def create_pipe_image(width, height, top=False):
    pipe_image = pygame.Surface((width, height))
    pipe_image.fill(GREEN)
    if top:
        cap_rect = pygame.Rect(0, height - 30, width, 30)
    else:
        cap_rect = pygame.Rect(0, 0, width, 30)
    pygame.draw.rect(pipe_image, (0, 200, 0), cap_rect)
    return pipe_image

bird_image = create_bird_image()

# --- Classes ---
class Bird(pygame.sprite.Sprite):
    def __init__(self):
        super().__init__()
        self.image = bird_image
        self.rect = self.image.get_rect(center=(100, SCREEN_HEIGHT // 2))
        self.velocity = 0

    def update(self):
        self.velocity += GRAVITY
        self.rect.y += int(self.velocity)

        if self.rect.top < 0:
            self.rect.top = 0
            self.velocity = 0
        if self.rect.bottom > SCREEN_HEIGHT:
            self.rect.bottom = SCREEN_HEIGHT
            self.velocity = 0

    def jump(self):
        self.velocity = JUMP_STRENGTH

class Pipe(pygame.sprite.Sprite):
    def __init__(self, x, y, is_top):
        super().__init__()
        self.is_top = is_top
        if self.is_top:
            height = y
            self.image = create_pipe_image(PIPE_WIDTH, height, top=True)
            self.rect = self.image.get_rect(bottomleft=(x, y - PIPE_GAP // 2))
        else:
            height = SCREEN_HEIGHT - y
            self.image = create_pipe_image(PIPE_WIDTH, height)
            self.rect = self.image.get_rect(topleft=(x, y + PIPE_GAP // 2))

    def update(self):
        self.rect.x -= PIPE_SPEED
        if self.rect.right < 0:
            self.kill()

# --- Functions ---
def draw_text(text, x, y):
    text_surface = font.render(text, True, WHITE)
    text_rect = text_surface.get_rect(center=(x, y))
    screen.blit(text_surface, text_rect)

def game_loop():
    bird = Bird()
    all_sprites = pygame.sprite.Group()
    pipes = pygame.sprite.Group()
    all_sprites.add(bird)

    score = 0
    pipe_spawn_timer = pygame.USEREVENT
    pygame.time.set_timer(pipe_spawn_timer, 1500)
    game_over = False
    game_started = False

    while True:
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                pygame.quit()
                sys.exit()

            if not game_started:
                if (event.type == pygame.KEYDOWN and event.key == pygame.K_SPACE) or (event.type == pygame.MOUSEBUTTONDOWN):
                    game_started = True
            else:
                if not game_over:
                    if (event.type == pygame.KEYDOWN and event.key == pygame.K_SPACE) or (event.type == pygame.MOUSEBUTTONDOWN):
                        bird.jump()

            if game_over:
                 if (event.type == pygame.KEYDOWN and event.key == pygame.K_SPACE) or (event.type == pygame.MOUSEBUTTONDOWN):
                     return # Restart game

            if event.type == pipe_spawn_timer and not game_over and game_started:
                pipe_y = random.randint(PIPE_GAP, SCREEN_HEIGHT - PIPE_GAP)
                top_pipe = Pipe(SCREEN_WIDTH, pipe_y, True)
                bottom_pipe = Pipe(SCREEN_WIDTH, pipe_y, False)
                all_sprites.add(top_pipe, bottom_pipe)
                pipes.add(top_pipe, bottom_pipe)


        # --- Update ---
        if game_started and not game_over:
            all_sprites.update()

            # Collision
            if pygame.sprite.spritecollide(bird, pipes, False) or bird.rect.top <= 0 or bird.rect.bottom >= SCREEN_HEIGHT:
                game_over = True

            # Score
            passed_pipes = [p for p in pipes if p.rect.right < bird.rect.left and not hasattr(p, 'passed')]
            for pipe in passed_pipes:
                 if pipe.is_top:
                    score += 1
                 pipe.passed = True # Mark as passed


        # --- Draw ---
        screen.fill(SKY_BLUE)
        all_sprites.draw(screen)

        if not game_started:
            draw_text("Click or Press Space to Start", SCREEN_WIDTH // 2, SCREEN_HEIGHT // 2)
        elif game_over:
            draw_text(f"Game Over! Score: {score}", SCREEN_WIDTH // 2, SCREEN_HEIGHT // 2 - 50)
            draw_text("Click or Press Space to Play Again", SCREEN_WIDTH // 2, SCREEN_HEIGHT // 2 + 50)
        else:
            draw_text(str(score), SCREEN_WIDTH // 2, 50)

        pygame.display.flip()
        clock.tick(60)

if __name__ == "__main__":
    while True:
        game_loop()