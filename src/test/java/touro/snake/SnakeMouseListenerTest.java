package touro.snake;

import org.junit.Test;

import java.awt.event.MouseEvent;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class SnakeMouseListenerTest {

    @Test
    public void mouseMoved_up() {
        //testing the scenario: event.getY() < head.getY()
        //given
        Snake snake = mock(Snake.class);
        Square head = mock(Square.class);
        SnakeMouseListener mouseListener = new SnakeMouseListener(snake);

        MouseEvent event = mock(MouseEvent.class);
        doReturn(70).when(event).getY();

        doReturn(head).when(snake).getHead();
        doReturn(100).when(head).getY();

        //when
        mouseListener.mouseMoved(event);

        //then
        verify(snake).turnTo(Direction.North);
    }

    @Test
    public void mouseMoved_right() {
        //testing the scenario: event.getX() < head.getX() && event.getX() < head.getY()
        //given
        Snake snake = mock(Snake.class);
        Square head = mock(Square.class);
        SnakeMouseListener mouseListener = new SnakeMouseListener(snake);

        MouseEvent event = mock(MouseEvent.class);
        doReturn(70).when(event).getX();
        doReturn(70).when(event).getY();

        doReturn(head).when(snake).getHead();
        doReturn(100).when(head).getX();
        doReturn(100).when(head).getY();

        //when
        mouseListener.mouseMoved(event);

        //then
        verify(snake).turnTo(Direction.West);
    }
}