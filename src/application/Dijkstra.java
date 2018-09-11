package application;

import javafx.event.*;
import javafx.fxml.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.*;
import java.util.*;
import javafx.util.Pair;
import javafx.scene.layout.*;
import javafx.scene.image.*;
import javafx.scene.text.Font;

//The class responsible for the program's backend
public class Dijkstra
{

    //Declarations
    public static Boolean f = false;
    public static int n, c = 1, beg, end, ans = 0, ansTry = 0;
    public static String stri, stri2, s1, s2;
    public static Vector<Vector<Integer>> a = new Vector<Vector<Integer>>();
    public static Vector<Vector<Pair<Integer,Integer>>> v = new Vector<Vector<Pair<Integer, Integer>>>(n+10);
    public static Vector<Integer> path = new Vector<Integer>();
    public static Vector<Integer> tempTry = new Vector<Integer>();
    public static Map<Integer, Integer> pathAli = new HashMap<Integer, Integer>();
    public static Map<String, Integer> mp = new HashMap<>();
    public static Map<Integer, String> map = new HashMap<>();
    public static Map<Integer, Boolean> vis = new HashMap<>();
    public static PriorityQueue<node> s = new PriorityQueue<node>();
    @FXML
    private TextField cityA;
    @FXML
    private TextField cityA1;
    @FXML
    private TextField cityB;
    @FXML
    private TextField dist;
    @FXML
    private TextField startc;
    @FXML
    private TextField endc;
    @FXML
    private Button ff;
    @FXML
    private Button closing2;
    @FXML
    private Button closing3;
    @FXML
    private TextField deleteEdge1;
    @FXML
    private TextField deleteEdge2;
    @FXML
    private TextField deleteNode;
    @FXML
    private Button closing6;
    @FXML
    private Button bckk;
    @FXML
    private Button close4;
    @FXML
    private Button yes1;
    @FXML
    private Button no1;
    @FXML
    private Button yes2;
    @FXML
    private Button no2;
    @FXML
    GridPane pathRepresentation = new GridPane();
    @FXML
    Label answer = new Label();
    @FXML
    Label dne;
    @FXML
    Label invalid2;
    @FXML
    Label invalid3;
    @FXML
    RadioButton dij;
    @FXML
    RadioButton flo;
    @FXML
    Label invalid;
    @FXML
    Label dne1;
    @FXML
    Label sure1;
    @FXML
    Label sure2;
    @FXML
    Label del;
    @FXML
    Label del1;

    //Method responsible for opening adding nodes/edges window
    public void done(ActionEvent event)
    {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("nodesnames.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            Scene scene1=new Scene(root1);
            scene1.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            stage.setScene(scene1);
            stage.setResizable(false);
            stage.setTitle("Add");
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/unnamed.png")));
            stage.show();
            Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
            stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 1.85);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    //Method responsible for openin remove nodes/edges window
    public void done1(ActionEvent event)
    {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("deleting.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            Scene scene1=new Scene(root1);
            scene1.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            stage.setScene(scene1);
            stage.setResizable(false);
            stage.setTitle("Delete");
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/unnamed.png")));
            stage.show();
            Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
            stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 1.85);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    /*
    The Next couple of methods are responsible for the close buttons in the windows
     */
    public void closing4(ActionEvent event)
    {
        Stage stage = (Stage) close4.getScene().getWindow();
        stage.close();
    }

    public void close2(ActionEvent event)
    {
        Stage stage = (Stage) closing2.getScene().getWindow();
        stage.close();
    }

    public void close3(ActionEvent event)
    {
        Stage stage = (Stage) closing3.getScene().getWindow();
        stage.close();
    }

    public void close6(ActionEvent event)
    {
        Stage stage = (Stage) closing6.getScene().getWindow();
        stage.close();
    }

    public void bck(ActionEvent event)
    {
        Stage stage = (Stage) bckk.getScene().getWindow();
        stage.close();
    }

    /*
    End of Closing buttons
     */

    //Method responsible for viewing alert message before deleting map from deleting menu
    public void suree1(ActionEvent event)
    {
        del.setVisible(false);
        del1.setVisible(false);
        yes1.setVisible(true);
        no1.setVisible(true);
        sure1.setVisible(true);
    }

    //Method responsible for proceeding with deleting map from deleting menu
    public void yess1(ActionEvent event)
    {
        yes1.setVisible(false);
        no1.setVisible(false);
        sure1.setVisible(false);
        resetgraph1();
    }

    //Method responsible for cancelling deleting map from deleting menu
    public void noo1(ActionEvent event)
    {
        dne.setVisible(false);
        yes1.setVisible(false);
        no1.setVisible(false);
        sure1.setVisible(false);
    }

    //Method responsible for viewing alert message before deleting map from updating menu
    public void suree2(ActionEvent event)
    {
        yes2.setVisible(true);
        no2.setVisible(true);
        sure2.setVisible(true);
    }

    //Method responsible for proceeding with deleting map from updating menu
    public void yess2(ActionEvent event)
    {
        yes2.setVisible(false);
        no2.setVisible(false);
        sure2.setVisible(false);
        resetgraph();
    }

    //Method responsible for cancelling deleting map from updating menu
    public void noo2(ActionEvent event)
    {
        yes2.setVisible(false);
        no2.setVisible(false);
        sure2.setVisible(false);
    }

    //Building Tree when two cities with road between them are entered
    public void actionPerformed(ActionEvent evt)
    {
        stri = cityA.getText();
        stri2 = cityB.getText();
        if (stri.length()==0||stri2.length()==0||dist.getText().length()==0)
        {
            invalid2.setVisible(true);
            invalid3.setVisible(false);
            cityA.clear();
            cityB.clear();
            dist.clear();
        }
        else
        {
            invalid3.setVisible(true);
            invalid2.setVisible(false);
            int w = Integer.parseInt(dist.getText());
            if (c == 1)
            {
                v.add(0,new Vector());
            }
            if (!mp.containsKey(stri))
            {
                v.add(c,new Vector());
                mp.put(stri, c);
                map.put(c++, stri);
            }
            if (!mp.containsKey(stri2))
            {
                v.add(c,new Vector());
                mp.put(stri2, c);
                map.put(c++, stri2);
            }
            v.get(mp.get(stri)).add(new Pair<Integer, Integer>(mp.get(stri2),w));
            v.get(mp.get(stri2)).add(new Pair<Integer, Integer>(mp.get(stri),w));
            cityA.clear();
            cityB.clear();
            dist.clear();
        }
    }

    //Adding a city to the tree
    public void actionPerformed1(ActionEvent evt)
    {
        String strii=cityA1.getText();
        if (strii.length()==0)
        {
            invalid2.setVisible(true);
            invalid3.setVisible(false);
            cityA1.clear();
        }
        else
        {
            invalid3.setVisible(true);
            invalid2.setVisible(false);
            if (!mp.containsKey(strii))
            {
                if(c == 1)
                {
                    v.add(0, new Vector());
                }
                v.add(c,new Vector());
                mp.put(strii, c);
                map.put(c++, strii);
                cityA1.clear();
            }
        }
    }

    //Representing the map connections
    public void show(ActionEvent deleteE)
    {
        TreeView<String> cities = new TreeView<String>();
        TreeItem<String> roots = new TreeItem<>("Cities");
        for (int i = 1; i < v.size(); i++)
        {
            if (map.get(i) == null)
            {
                continue;
            }
            TreeItem<String> root = new TreeItem<>(map.get(i));
            for (int j = 0; j < v.get(i).size(); j++)
            {
                if (map.get(v.get(i).get(j).getKey()) == null)
                {
                    continue;
                }
                TreeItem<String> node = new TreeItem<>("Connected to " + map.get(v.get(i).get(j).getKey()) + ",    Distances between them is:   " + v.get(i).get(j).getValue());
                root.getChildren().add(node);
            }
            roots.getChildren().add(root);
        }
        cities.setRoot(roots);
        try
        {
            Button but = new Button("Close");
            Pane root = new Pane();
            Stage stage = new Stage();
            but.setOnAction(new EventHandler<ActionEvent>()
            {
                @Override
                public void handle(ActionEvent event)
                {
                    Stage stage = (Stage) but.getScene().getWindow();
                    stage.close();
                }
            });
            but.setLayoutX(540);
            but.setLayoutY(460);
            Image wasl = new Image(getClass().getResourceAsStream("/unnamed.png"));
            ImageView waslne = new ImageView(wasl);
            waslne.setOpacity(0.5);
            waslne.setLayoutX(50);
            waslne.setLayoutY(150);
            waslne.setFitWidth(150);
            waslne.setFitHeight(150);
            cities.setPrefSize(500.0,500.0);
            root.getChildren().addAll(waslne,cities,but);
            Scene scene1 = new Scene(root,600,500);
            scene1.getStylesheets().add(getClass().getResource("application2.css").toExternalForm());
            stage.setResizable(false);
            stage.setScene(scene1);
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/unnamed.png")));
            stage.setTitle("map");
            stage.show();
            Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
            stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 1.85);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    //Deleting edge
    public void delete_edge(ActionEvent deleteE)
    {
        int x1, x2, y;
        String nod1, nod2;
        nod1 = deleteEdge1.getText();
        nod2 = deleteEdge2.getText();
        if(nod1.length()>0&&nod2.length()>0)
        {
            del.setVisible(true);
            del1.setVisible(false);
            if(mp.containsKey(nod1)&&mp.containsKey(nod2))
            {
                x1 = mp.get(nod1);
                x2 = mp.get(nod2);

                for (int i = 0; i < v.get(x1).size(); i++)
                {
                    y = v.get(x1).get(i).getKey();
                    if (y == x2)
                    {
                        for (int j = 0; j < v.get(y).size(); j++)
                        {
                            if (v.get(y).get(j).getKey() == x1)
                            {

                                v.get(y).remove(j);
                                break;
                            }
                        }
                        v.get(x1).remove(i);
                        break;
                    }
                }
            }

        }
        else
        {
            del.setVisible(false);
            del1.setVisible(true);
        }
        deleteEdge1.clear();
        deleteEdge2.clear();
    }

    ///Deleting nodes
    public  void delete_node (ActionEvent event)
    {
        String nod = deleteNode.getText();
        if(nod.length()>0)
        {
            del.setVisible(true);
            del1.setVisible(false);
            if(mp.containsKey(nod))
            {
                int x = mp.get(nod);
                v.get(mp.get(nod)).clear();
                for (int i = 1; i < c; i++)
                {
                    for (int j = 0; j < v.get(i).size(); j++)
                    {
                        if (mp.get(nod) == v.get(i).get(j).getKey())
                        {
                            v.get(i).remove(j--);
                        }
                    }
                }
                map.remove(x);
                mp.remove(nod);
            }
            deleteNode.clear();
            reset();
        }
        else
        {
            del.setVisible(false);
            del1.setVisible(true);
        }
    }
    //Reset variables to be used in another way finding
    public static void reset()
    {
        pathAli.clear();
        path.clear();
        vis.clear();
        tempTry.clear();
        f = false;
        s.clear();
        a.clear();
        ans = 0;
    }

    //Deleting entire map from update menu
    public void resetgraph()
    {
        dne1.setVisible(true);
        pathAli.clear();
        path.clear();
        vis.clear();
        tempTry.clear();
        f = false;
        s.clear();
        a.clear();
        v.clear();
        mp.clear();
        map.clear();
        c = 1;
    }

    //Deleting entire map from deleting menu
    public void resetgraph1()
    {
        dne.setVisible(true);
        pathAli.clear();
        path.clear();
        vis.clear();
        tempTry.clear();
        f = false;
        s.clear();
        a.clear();
        v.clear();
        mp.clear();
        map.clear();
        c = 1;
    }

    //Finding path
    public void letsfind(ActionEvent evt)
    {
        s1 = startc.getText();
        s2 = endc.getText();
        if(s1.length() == 0 || s2.length() ==0)
        {
            invalid.setVisible(true);
        }
        else
        {
            if (mp.containsKey(s2) && mp.containsKey(s1))
            {
                beg = mp.get(s1);
                end = mp.get(s2);
                if (flo.isSelected())
                {
                    floyd();
                }
                else
                {
                    dijkstra(s1, s2);
                }
            }
            startc.clear();
            endc.clear();
            present(s1, s2);
            reset();
            Stage stagee = (Stage) ff.getScene().getWindow();
            stagee.close();
        }
    }

    //Sorting nodes inside priority queue (s)
    public static class node implements Comparable<node>
    {
        int cost, num, parent;
        public node(int c, int num, int p)
        {
            this.num = num;
            this.cost = c;
            this.parent = p;
        }
        @Override
        public int compareTo(node n)
        {
            if (cost != n.cost)
                return cost - n.cost;
            return num - n.num;
        }
    }

    //Dijkstra Algorithm
    public void dijkstra(String start,String finish)
    {
        boolean vis[] = new boolean[c + 5];
        s.add(new node(0,mp.get(start),-1));
        int crnt = -1;
        while(!s.isEmpty())
        {
            node u = s.remove();
            if (vis[u.num])
            {
                continue;
            }
            vis[u.num] = true;
            pathAli.put(u.num, u.parent);
            if (u.num == mp.get(finish))
            {
                ans = u.cost;
                crnt = mp.get(finish);
                break;
            }
            for (int i = 0; i < v.get(u.num).size(); i++)
            {
                s.add(new node(u.cost + v.get(u.num).get(i).getValue(), v.get(u.num).get(i).getKey(), u.num));
            }
        }
        while(crnt!=-1)
        {
            path.add(crnt);
            crnt = pathAli.get(crnt);
        }
        if(path.size()>0)
        {
            Collections.reverse(path);
        }
    }

    //Floyd Algorithm
    public void floyd()
    {
        int num1, num2;
        for (int i = 0; i < c; i++)
        {
            a.add(i, new Vector<Integer>());
        }
        for (int i = 0; i < c; i++)
        {
            for (int j = 0; j < c; j++)
            {
                if (i == j)
                {
                    a.get(i).add(j, 0);
                }
                else
                {
                    a.get(i).add(j, 10000000);
                }
            }
        }
        for (int i = 1; i < c; i++)
        {
            for (int j = 0; j < v.get(i).size(); j++)
            {
                num1 = v.get(i).get(j).getKey();
                num2 = v.get(i).get(j).getValue();
                a.get(i).set(num1, Math.min(num2, a.get(i).get(num1)));
                a.get(num1).set(i, Math.min(num2, a.get(num1).get(i)));
            }
        }
        for(int k = 1; k < c; k++)
        {
            for(int i = 1; i < c; i++)
            {
                for(int j = 1; j < c; j++)
                {
                    if (a.get(i).get(k) + a.get(k).get(j) < a.get(i).get(j))
                    {
                        a.get(i).set(j, Math.min(a.get(i).get(j), a.get(i).get(k) + a.get(k).get(j)));
                    }
                }
            }
        }
        if (a.get(beg).get(end) == 10000000)
        {
            return;
        }
        tempTry.add(beg);
        ans = a.get(beg).get(end);
        vis.put(beg, true);
        solve(beg);
    }

    //Finding path nodes if floyd algorithm is used
    public void solve(int nodee)
    {
        if (ansTry > a.get(beg).get(end) || f)
        {
            return;
        }
        if (ansTry == a.get(beg).get(end))
        {
            if ((nodee == end) && (path.size() == 0 || tempTry.size() < path.size()))
            {
                path.clear();
                for (int i = 0; i < tempTry.size(); i++)
                {
                    path.add(tempTry.get(i));
                }
            }
            return;
        }
        for (int i = 0; i < v.get(nodee).size(); i++)
        {
            if (!vis.containsKey(v.get(nodee).get(i).getKey()) || !vis.get(v.get(nodee).get(i).getKey()))
            {
                if (vis.containsKey(v.get(nodee).get(i).getKey()))
                {
                    vis.replace(v.get(nodee).get(i).getKey(), true);
                }
                else
                {
                    vis.put(v.get(nodee).get(i).getKey(), true);
                }
                tempTry.add(v.get(nodee).get(i).getKey());
                ansTry += v.get(nodee).get(i).getValue();
                solve(v.get(nodee).get(i).getKey());
                if (tempTry.size() > 1)
                {
                    ansTry -= v.get(nodee).get(i).getValue();
                    tempTry.remove(tempTry.size() - 1);
                }
                vis.replace(v.get(nodee).get(i).getKey(), false);
            }
        }
    }

    //Representing the shortest path nodes
    public void present(String bege, String ende)
    {
        int indi = -1, indj = -1, ind = 0;
        Image down = new Image(getClass().getResourceAsStream("/down_right.png"));
        Image right = new Image (getClass().getResourceAsStream("/right_arrow.png"));
        Image left = new Image(getClass().getResourceAsStream("/left_arrow.png"));
        Image downLeft = new Image(getClass().getResourceAsStream("/down_left.png"));
        Image rightdown = new Image(getClass().getResourceAsStream("/startrightdown.png"));
        Image st = new Image (getClass().getResourceAsStream("/start.png"));
        Image fn = new Image(getClass().getResourceAsStream("/finish.png"));
        pathRepresentation.setPadding(new Insets(0,0,0,20));
        ColumnConstraints cc = new ColumnConstraints();
        cc.setHgrow(Priority.ALWAYS);
        RowConstraints rc = new RowConstraints();
        rc.setVgrow(Priority.ALWAYS);
        pathRepresentation.getColumnConstraints().add(cc);
        pathRepresentation.getRowConstraints().add(rc);
        for (int i = 0; i < 9 && path.size() > 0; i++)
            pathRepresentation.getColumnConstraints().add(cc);
        for (int i = 0; i < 9 && path.size() > 0; i++)
            pathRepresentation.getRowConstraints().add(rc);
        if (path.size() == 0)
        {
            Label lbl = new Label();
            lbl.setText("There is no road between " + bege + " and " + ende);
            lbl.setFont(Font.font(50));
            pathRepresentation.setPadding(new Insets(100,100,100,175));
            pathRepresentation.add(lbl, 0, 0);
        }
        if (path.size() == 1)
        {
            Label lbl = new Label();
            lbl.setFont(Font.font(50));
            lbl.setText("You are Already in " + ende);
            pathRepresentation.setPadding(new Insets(150,150,150,175));
            pathRepresentation.add(lbl,0, 0);
        }
        for (int i = 0; i < 10 && path.size() > 1 && ind < path.size(); i++)
        {
            for (int j = 0; j < 10 && path.size() > 1 && ind < path.size(); j++)
            {
                Label lbl = new Label();
                if (i == 0 && j == 0)
                {
                    lbl.setGraphic(new ImageView(st));
                    pathRepresentation.add(lbl, 0, 0);
                    continue;
                }
                if (i % 2 == 0 && j == 9)
                {
                    lbl.setGraphic(new ImageView(down));
                    pathRepresentation.add(lbl, j, i);
                }
                else if (i % 2 == 0 && j == 0)
                {
                    lbl.setGraphic(new ImageView(rightdown));
                    pathRepresentation.add(lbl, j, i);
                }
                else if (i % 2 == 0 && j % 2 == 1)
                {
                    lbl.setText(map.get(path.get(ind++)));
                    lbl.setFont(Font.font(30));
                    pathRepresentation.add(lbl, j, i);
                }
                else if (i % 2 == 0 && j % 2 == 0)
                {
                    lbl.setGraphic(new ImageView(right));
                    pathRepresentation.add(lbl, j, i);
                }
                else if (i % 2 == 1 && j == 9)
                {
                    lbl.setGraphic(new ImageView(downLeft));
                    pathRepresentation.add(lbl, 10 - j - 1, i);
                }
                else if (i % 2 == 1 && j % 2 == 0)
                {
                    lbl.setText(map.get(path.get(ind++)));
                    lbl.setFont(Font.font(30));
                    pathRepresentation.add(lbl, 10 - j - 1, i);
                }
                else if (i % 2 == 1 && j % 2 == 1)
                {
                    lbl.setGraphic(new ImageView(left));
                    pathRepresentation.add(lbl, 10 - j - 1, i);
                }
                if (ind == path.size() && i % 2 == 0)
                {
                    indi = i;
                    indj = j + 1;
                }
                else if (ind == path.size())
                {
                    indi = i;
                    indj = 10 - j - 2;
                }
            }
        }
        if(indi > -1)
        {
            Label lble=new Label();
            lble.setGraphic(new ImageView(fn));
            pathRepresentation.add(lble, indj, indi);
        }
        if (path.size() > 1)
        {
            answer.setText("Total distance from " + bege + " to " + ende + " is  " + Integer.toString(ans));
        }
        if (path.size() == 1)
        {
            answer.setText("Total distance is ZERO!!");
        }
        answer.setFont(Font.font(50));
        try
        {
            StackPane root1 =  new StackPane();
            Label a = new Label();
            a = answer;
            a.setOpacity(0.25);
            Stage stage = new Stage();
            root1.getChildren().addAll(pathRepresentation,a);
            Scene scene1=new Scene(root1,1200,800);
            scene1.getStylesheets().add(getClass().getResource("application2.css").toExternalForm());
            stage.setMaximized(true);
            stage.setScene(scene1);
            stage.setTitle("Path");
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/unnamed.png")));
            stage.show();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        path.clear();
    }
}
