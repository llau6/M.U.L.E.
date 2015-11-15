package MULE.Controller;

import javafx.embed.swing.JFXPanel;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import org.junit.Before;
import org.junit.Test;
import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNull;

public class ControllerWampusGroundsTest {
    private GridPane testMap;

    public class TestNode extends Button {
        private String name;
        public TestNode(String inputName) {
            name = inputName;
        }

        public String getName() {
            return name;
        }
    }
    @Before
    public void setUp() throws Exception {
        JFXPanel jfxPanel = new JFXPanel();
        testMap = new GridPane();
    }
    //GridPane.add(Node, col, row)
    //getButtonFromGrid(GridPane, row, col)
    public void makeGridPane (int n) {
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                testMap.add(new TestNode(row + "th row " + col + "th col node"), col, row);
            }
        }
        testMap.add(new Label("hi"), 5, 5);
    }
    public void makeGridPaneNames() {
        testMap.add(new TestNode("Jatin"), 0, 0);
        testMap.add(new TestNode("Lily"), 1, 0);
        testMap.add(new TestNode("Ellie"), 0, 1);
        testMap.add(new TestNode("Sally"), 1, 1);
        testMap.add(new TestNode("Jeremy"), 2, 1);
    }

    @Test
    public void testGetTestNode10() {
        makeGridPane(10);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                assertEquals(i + "th row " + j + "th col node",
                        ((TestNode) ControllerWampusGrounds.getButtonFromGridPane(testMap, i, j)).getName());
            }
        }
    }

    @Test
    public void testNonButton() {
        makeGridPane(5);
        testMap.add(new Label("hi"), 10, 3);
        assertNull(ControllerWampusGrounds.getButtonFromGridPane(testMap, 10, 2));
    }
    @Test
    public void testGetButtons() {
        Button button1 = new Button("hello");
        Button button2 = new Button("hi");
        Button button3 = new Button("nihao");
        testMap.add(button1, 0, 0);
        testMap.add(button2, 0, 1);
        testMap.add(button3, 10, 5);
        assertEquals("hello", ((Button) ControllerWampusGrounds.getButtonFromGridPane(testMap, 0, 0)).getText());
        assertEquals(button1, ControllerWampusGrounds.getButtonFromGridPane(testMap, 0, 0));
        assertEquals("hi", ((Button) ControllerWampusGrounds.getButtonFromGridPane(testMap, 1, 0)).getText());
        assertEquals(button2, ControllerWampusGrounds.getButtonFromGridPane(testMap, 1, 0));
        assertEquals("nihao", ((Button) ControllerWampusGrounds.getButtonFromGridPane(testMap, 5, 10)).getText());
        assertEquals(button3, ControllerWampusGrounds.getButtonFromGridPane(testMap, 5, 10));

    }
    @Test
    public void testGetButtoneNames() {
        makeGridPaneNames();
        assertEquals("Jatin", ((TestNode) ControllerWampusGrounds.getButtonFromGridPane(testMap, 0, 0)).getName());
        assertEquals("Lily", ((TestNode) ControllerWampusGrounds.getButtonFromGridPane(testMap, 0, 1)).getName());
        assertEquals("Ellie", ((TestNode) ControllerWampusGrounds.getButtonFromGridPane(testMap, 1, 0)).getName());
        assertEquals("Sally", ((TestNode) ControllerWampusGrounds.getButtonFromGridPane(testMap, 1, 1)).getName());
        assertEquals("Jeremy", ((TestNode) ControllerWampusGrounds.getButtonFromGridPane(testMap, 1, 2)).getName());
    }

    @Test (timeout = 200, expected = IllegalArgumentException.class)
    public void testNullGrid() {
        ControllerWampusGrounds.getButtonFromGridPane(null, 0,0);
    }
    @Test (timeout = 200, expected = IllegalArgumentException.class)
    public void testNegRow() {
        ControllerWampusGrounds.getButtonFromGridPane(testMap, -2, 0);
    }
    @Test (timeout = 200, expected = IllegalArgumentException.class)
    public void testNegCol() {
        ControllerWampusGrounds.getButtonFromGridPane(testMap, 0, -3);
    }
    @Test
    public void testGetButtonNull () {
        assertNull(ControllerWampusGrounds.getButtonFromGridPane(testMap, 0, 0));
    }
}