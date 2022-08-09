import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class JavaCrud {
    //JPanel privado principal;
    private JPanel MainPanel;
    private JTextField nombreTF;
    private JTextField precioTF;
    private JTextField ciudadTF;
    private JButton crearButton;
    private JButton deleteButton;
    private JButton upDateButton;
    private JTextField IDTF;
    private JTextField cantidadTF;
    private JButton limpiarButton;
    private JButton buscarButton;
    private JLabel textMensaje;

    public static void main(String[] args) {
        JFrame frame = new JFrame("JavaCrud");
        frame.setContentPane(new JavaCrud().MainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack(); //elementos empaquetados
        frame.setVisible(true);
    }

    public JavaCrud() {
        Connect();
        crearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {Create();}

        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {Delete();}
        });
        upDateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { Update();}
        });
        limpiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {limpiar();}
        });
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {Buscar();}
        });
    }//termina public JavaCRUD

    Connection conn;
    PreparedStatement pst;
    public void Connect(){
        final String DB_URL="jdbc:mysql://localhost/misproductos?serverTimezone=UTC";
        final String USERNAME="nicole";
        final String PASSWORD="030921";


        try{
            Connection conn= DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
            Statement stmt= conn.createStatement();

            System.out.println("Conexion exitosa");

        } catch(SQLException ex){
            ex.printStackTrace();
            System.out.println("SQL incorrecto");

        }
    }

    public void Create(){

            String nombre,precio,ciudad,id,cantidad;
            nombre=nombreTF.getText();
            precio=precioTF.getText();
            ciudad=ciudadTF.getText();
            id=IDTF.getText();
            cantidad=cantidadTF.getText();

            System.out.println(nombre);
            System.out.println(precio);
            System.out.println(ciudad);
            System.out.println(id);
            System.out.println(cantidad);

            final String DB_URL="jdbc:mysql://localhost/misproductos?serverTimezone=UTC";
            final String USERNAME="nicole";
            final String PASSWORD="030921";


            try {
                Connection conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
                Statement stmt= conn.createStatement();
                String sql ="insert into productos (pnombre,pcuidad,pprecio,pcantidad)values (?,?,?,?)";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setString(1,nombre);
                pst.setString(2,ciudad);
                pst.setString(3,precio);
                pst.setString(4,cantidad);
                //ResultSet resultSet = pst.executeQuery();
                pst.executeUpdate();
                System.out.println("Coneccion Exitosa");

                stmt.close();
                conn.close();
            } catch (SQLException ex){
                ex.printStackTrace();
                System.out.printf("ERROR: SQL incorrecto");
        }
      }
    public void limpiar(){
        nombreTF.setText("");
        precioTF.setText("");
        ciudadTF.setText("");
        IDTF.setText("");
        cantidadTF.setText("");
    }

    public void Buscar (){
        String id="0";
        id=IDTF.getText();

        final String DB_URL="jdbc:mysql://localhost/misproductos?serverTimezone=UTC";
        final String USERNAME="nicole";
        final String PASSWORD="030921";


        try {
            Connection conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
            Statement stmt= conn.createStatement();
            String sql ="select * from productos where productID=?";
            pst.setString(1,id);
            //PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs=pst.executeQuery();

            System.out.println("Coneccion Exitosa");
            if (rs.next()==true){
                String nombre,ciudad,precio,cantidad;
                nombre=rs.getString(2);
                ciudad=rs.getString(3);
                precio=rs.getString(4);
                cantidad=rs.getString(5);

                System.out.println();
                nombreTF.setText(nombre);
                ciudadTF.setText(ciudad);
                precioTF.setText(precio);
                cantidadTF.setText(cantidad);

            }else{
                //textMensaje.setText("No se encuentra el producto");
                JOptionPane.showMessageDialog(null,"No se encuentra el producto");
                limpiar();//limpia automaticamente
            }

            stmt.close();
            conn.close();
        } catch (SQLException ex){
            ex.printStackTrace();
            System.out.printf("ERROR: SQL incorrecto");
        }

    }

    public void Update (){
        String id, nombre, ciudad, precio, cantidad;
        id=IDTF.getText();
        nombre=nombreTF.getText();
        ciudad=ciudadTF.getText();
        precio=precioTF.getText();
        cantidad=cantidadTF.getText();

        final String DB_URL="jdbc:mysql://localhost/misproductos?serverTimezone=UTC";
        final String USERNAME="nicole";
        final String PASSWORD="030921";


        try {
            Connection conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
            Statement stmt= conn.createStatement();
            String sql ="update productos set pnombre=?,pcuidad=?,pprecio=?,pcantidad=? where productID=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1,nombre);
            pst.setString(2,ciudad);
            pst.setString(3,precio);
            pst.setString(4,cantidad);
            pst.setString(5,id);
            //ResultSet resultSet = pst.executeQuery();
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null,"Registro Actualizado");

            stmt.close();
            conn.close();
        } catch (SQLException ex){
            ex.printStackTrace();
            System.out.printf("ERROR: SQL incorrecto");
        }
    }

    public void Delete(){
        final String DB_URL="jdbc:mysql://localhost/misproductos?serverTimezone=UTC";
        final String USERNAME="nicole";
        final String PASSWORD="030921";

        String borrarid=IDTF.getText();

        try {
            Connection conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
            Statement stmt= conn.createStatement();
            String sql ="delete from productos where productID=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1,borrarid);

            pst.executeUpdate();
            JOptionPane.showMessageDialog(null,"Registro borrado");

            stmt.close();
            conn.close();
        } catch (SQLException ex){
            ex.printStackTrace();
            System.out.printf("ERROR: SQL incorrecto");
        }

    }

  }//fin public class JavaCrud
