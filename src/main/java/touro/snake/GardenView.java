package touro.snake;

import touro.snake.strategy.SnakeStrategy;
import touro.snake.strategy.astar.schwimmer.AStarStrategy;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GardenView extends JComponent {

    private final Garden garden;
    public static final int CELL_SIZE = 10;

    public GardenView(Garden garden) {
        this.garden = garden;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintGrass(g);
        paintFood(g);
        paintSnake(g);
        paintOpen(g);
        paintPath(g);
    }

    private void paintOpen(Graphics g) {
        List<Square> searchSpace = garden.getSnake().getStrategy().getSearchSpace();
        if (searchSpace != null) {
            g.setColor(Color.CYAN);
            for (Square s : searchSpace) {
                g.fillRect(s.getX() * CELL_SIZE, s.getY() * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            }
        }
    }

    private void paintPath(Graphics g) {
        List<Square> path = garden.getSnake().getStrategy().getPath();
        if (path != null) {
            g.setColor(Color.YELLOW);
            for (Square s : path) {
                g.fillRect(s.getX() * CELL_SIZE, s.getY() * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            }
        }
    }

    void paintGrass(Graphics g) {
        // Berger
        g.setColor(Color.GREEN);
        g.fillRect(0, 0, getWidth(), getHeight());
    }

    void paintSnake(Graphics g) {
        g.setColor(Color.RED);
        for (Square s : garden.getSnake().getSquares()) {
            g.fillRect(s.getX() * CELL_SIZE, s.getY() * CELL_SIZE, CELL_SIZE, CELL_SIZE);
        }
    }

    void paintFood(Graphics g) {
        // Berger
        if (garden.getFood() != null) {
            Food food = garden.getFood();
            g.setColor(Color.LIGHT_GRAY);

            int x = food.getX() * CELL_SIZE;
            int y = food.getY() * CELL_SIZE;
            g.fillRect(x, y, CELL_SIZE, CELL_SIZE);
        }
    }
}
