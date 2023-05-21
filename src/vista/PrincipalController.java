/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package vista;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author mnu24
 */
public class PrincipalController implements Initializable {
    
    private String rutaConexion;
    private String userConexion;
    private String passwordConexion;
    private String puertoConexion;
    private String databaseConexion;
    final String driver = "com.mysql.cj.jdbc.Driver";
    private Connection cx;
    final List<String> lasColumnas = List.of("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s");
    private List<String> listaColumnasCons = new ArrayList<>();
    private int numeroColumnasConsulta = 0;
    
    @FXML
    private Menu menuInic;
    @FXML
    private Menu menuBaseDato;
    @FXML
    private Menu menuTabl;
    @FXML
    private Menu menuCons;
    @FXML
    private Pane paneInicio;
    @FXML
    private TextField paneInicTextURL;
    @FXML
    private TextField paneInicTextDataBase;
    @FXML
    private Button paneInicBotoLogi;
    @FXML
    private TextField paneInicTextPuer;
    @FXML
    private TextField paneInicTextUser;
    @FXML
    private TextField paneInicTextPass;
    @FXML
    private Pane paneBaseDato;
    @FXML
    private Button paneBaseDatoBotoSele;
    @FXML
    private Button paneBaseDatoBotoElim;
    @FXML
    private Button paneBaseDatoBotoCrear;
    @FXML
    private TextField paneBaseDatoTextNomb;
    @FXML
    private MenuBar menuBar;
    @FXML
    private ListView<String> paneBaseDatoList;
    @FXML
    private Pane paneTablTablModi;
    @FXML
    private ComboBox<String> paneTablTablModiComb;
    @FXML
    private Menu paneTablTablModilabe;
    @FXML
    private TableView<ObjetoDinamico> paneTablTablModiTableView;
    @FXML
    private ComboBox<String> paneConsCombTabl1;
    @FXML
    private CheckBox paneConsChec1;
    @FXML
    private CheckBox paneConsChec2;
    @FXML
    private ComboBox<String> paneConsCombTabl2;
    @FXML
    private ComboBox<String> paneConsCombRelaTabl1;
    @FXML
    private ComboBox<String> paneConsCombRelaTabl2;
    @FXML
    private ComboBox<String> paneConsCombOper1;
    @FXML
    private ComboBox<String> paneConsCombCond1;
    @FXML
    private ComboBox<String> paneConsCombCond2;
    @FXML
    private ComboBox<String> paneConsCombOper2;
    @FXML
    private TextField paneConsTextValo1;
    @FXML
    private TextField paneConsTextValo2;
    @FXML
    private Pane paneCons;
    @FXML
    private ListView<String> paneConsListColmT1;
    @FXML
    private ListView<String> paneConsListColmT2;
    @FXML
    private Button paneConsButoListT1;
    @FXML
    private Button paneConsButoListT2;
    @FXML
    private TableView<ObjetoDinamico> paneConsTablView;
    @FXML
    private ComboBox<String> paneTablTablModiCombAlter;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.paneInicio.setVisible(true);
        this.paneInicio.toFront();
        this.paneInicTextURL.setText("localhost");
        this.paneInicTextDataBase.setText("sys");
        this.paneInicTextPuer.setText("3306");
        this.menuBaseDato.setDisable(true);
        this.menuCons.setDisable(true);
        this.menuTabl.setDisable(true);
       
    }    
    

    @FXML
    private void doMenuInicio(ActionEvent event) {
        this.desconectar();
        this.paneInicio.setVisible(true);
        this.paneInicio.toFront();
        this.paneInicTextPass.clear();
    }

    @FXML
    private void doMenuBasesDeDatos(ActionEvent event){
        this.paneBaseDato.setVisible(true);
        this.paneBaseDato.toFront();
        this.llenadoTablaBaseDato();
    }

    @FXML
    private void doMenuConsultas(ActionEvent event) {
        this.paneCons.setVisible(true);
        this.paneCons.toFront();
        this.llenadoComboTablas(this.paneConsCombTabl1);
        this.llenadoComboTablas(this.paneConsCombTabl2);
        this.llenarComboOperadores();
        this.paneConsCombTabl1.getSelectionModel().selectFirst();
        this.paneConsCombTabl2.getSelectionModel().selectLast();
        this.llenadoComboColumnas();
        this.limpiarConsulta();
    }

    @FXML
    private void doLogin(ActionEvent event) {
        
        this.rutaConexion = this.paneInicTextURL.getText();
        this.puertoConexion = this.paneInicTextPuer.getText();
        this.userConexion = this.paneInicTextUser.getText();
        this.passwordConexion = this.paneInicTextPass.getText();
        this.databaseConexion = this.paneInicTextDataBase.getText();
        this.conectarPrimario();
        this.paneTablTablModilabe.setText("Se encuentra conectado a: " + this.databaseConexion);
       
    }

    @FXML
    private void doSeleccionarBaseDato(ActionEvent event) {
        this.usarBaseDato();
        this.paneTablTablModilabe.setText("Se encuentra conectado a: " + this.databaseConexion);
    }

    @FXML
    private void doEliminarBaseDato(ActionEvent event) {
        if(JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea eliminar la base de datos?")==0){
            this.eliminarBaseDato();
        }  
    }

    @FXML
    private void doCrearBaseDato(ActionEvent event) {
        this.crearBaseDato();
    }
    
    @FXML
    private void doMenuModiTabl(ActionEvent event) {
        this.paneTablTablModi.setVisible(true);
        this.paneTablTablModi.toFront();
        this.llenadoComboTablas(this.paneTablTablModiComb);
        this.paneTablTablModiCombAlter.getItems().clear();
        this.paneTablTablModiCombAlter.getItems().add("add");
        this.paneTablTablModiCombAlter.getItems().add("change");
        this.paneTablTablModiCombAlter.getItems().add("modify");
        this.paneTablTablModiCombAlter.getItems().add("drop");
    }
    
    @FXML
    private void doAccionPaneConsCombTabl1(ActionEvent event) {
        this.llenadoComboColumnas();
        this.llenadoListasTablas();
    }

    @FXML
    private void doAccionPaneConsCombTabl2(ActionEvent event) {
        this.llenadoComboColumnas();
        this.llenadoListasTablas();
    }

    @FXML
    private void doEliminarTabla(ActionEvent event) {
        if(JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea eliminar "+this.paneTablTablModiComb.getSelectionModel().getSelectedItem()+"?")==0){
            this.eliminaTabla();
        }
    }

    @FXML
    private void doCrearTabla(ActionEvent event) {
        this.creaNuevaTabla();
    }
    
    @FXML
    private void doInspeccionarTabla(ActionEvent event) {
        this.inspeccionarTabl();
    }

    @FXML
    private void doModificarRegistro(ActionEvent event) {
        this.modificarRegistro();
    }

    @FXML
    private void doNuevoRegistro(ActionEvent event) {
        this.nuevoRegistro();
    }

    @FXML
    private void doEliminarRegistro(ActionEvent event) {
        if(JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea eliminar el Registro?")==0){
            this.eliminarRegistro();
        }  
    }
    
    @FXML
    private void doAccionPaneTablCombTabl(ActionEvent event) {
        this.paneTablTablModiTableView.getColumns().clear();
        this.creaTabla(this.paneTablTablModiComb.getSelectionModel().getSelectedItem());
        this.llenaTabla(this.getTodaTabla());
    }
    
    @FXML
    private void doAñadirColumnaTabl1(ActionEvent event) {
        String columna = this.paneConsListColmT1.getSelectionModel().getSelectedItem();
        this.añadeColumna(columna);
        this.listaColumnasCons.add(this.paneConsCombTabl1.getSelectionModel().getSelectedItem() + "." + columna);
        
    }

    @FXML
    private void doAñadirColumnaTabl2(ActionEvent event) {
        String columna = this.paneConsListColmT2.getSelectionModel().getSelectedItem();
        this.añadeColumna(columna);
        this.listaColumnasCons.add(this.paneConsCombTabl2.getSelectionModel().getSelectedItem() + "." + columna);
    }
    
    @FXML
    private void doConsulta(ActionEvent event) {
        this.realizaConsulta();
        this.paneConsTablView.setEditable(true);
    }
    
    @FXML
    private void doConsultaLimpiar(ActionEvent event) {
        this.limpiarConsulta();
    }
    
    @FXML
    private void doCrearVista(ActionEvent event) {
        this.creaVista();
    }
    
    @FXML
    private void doEliminarVista(ActionEvent event) {
        this.eliminaVista();
    }
    
    @FXML
    private void doModificarTabla(ActionEvent event) {
        this.modificaTabla();
    }
    
    @FXML
    private void doEliminarRegistroConsulta(ActionEvent event) {
        this.eliminarRegistroConsulta();
    }

    
    
    //------------------------ /BASES DE DATOS -------------------------
    
    private void usarBaseDato(){
        this.databaseConexion = this.paneBaseDatoList.getSelectionModel().getSelectedItem();

        if(this.databaseConexion != null){
            try{
            Statement sql = this.cx.createStatement();
            String query = "use " + this.databaseConexion + ";";
            sql.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Se esta utilizando la base de datos: " + this.databaseConexion);
            
            sql.close();
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null, ex);
        
            }
        }
    }
    
    private void eliminarBaseDato(){
        String baseSeleccionada = this.paneBaseDatoList.getSelectionModel().getSelectedItem();
 
        if(baseSeleccionada != null){
            try{
            Statement sql = this.cx.createStatement();
            String query = "drop database " + baseSeleccionada + ";";
            sql.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Se ha borrado la base de datos: " + baseSeleccionada);
            this.llenadoTablaBaseDato();
            
            sql.close();
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null, ex);
        
            }
        }
    }
    
    private void crearBaseDato(){
        String nombreBaseNueva = this.paneBaseDatoTextNomb.getText();
        
        if(!nombreBaseNueva.equals("")){
            try{
            Statement sql = this.cx.createStatement();
            String query = "create database " + nombreBaseNueva + ";";
            sql.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Se ha creado la base de datos: " + nombreBaseNueva);
            this.llenadoTablaBaseDato();
            
            sql.close();
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null, ex);
        
            }
        }else{
            JOptionPane.showMessageDialog(null, "Debe darle un nombre a la base de datos");
        }
    }
    
    private ResultSet getBaseDato(){
        
        ResultSet resultado = null;
        
        try{
            Statement sql = this.cx.createStatement();
            String query = "show databases;";
            resultado = sql.executeQuery(query);
          
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
        
        }
        return resultado;
    }
    
   private void llenadoTablaBaseDato(){
       List<String> lista = new ArrayList<>();
       lista.clear();
       ResultSet result = null;
       
       try {       
            result = this.getBaseDato();
            
            while(result.next()){
                lista.add(result.getString(1));
            }
            result.close();
        } catch (SQLException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        ObservableList<String> namesDB = FXCollections.observableArrayList(lista);
        this.paneBaseDatoList.setItems(namesDB);
        
        
   }
   
   //---------------------------- BASES DE DATOS/ ---------------------------
   
   //---------------------------- /REGISTROS ---------------------------------
   private void eliminarRegistro(){
        ObjetoDinamico registro = this.paneTablTablModiTableView.getSelectionModel().getSelectedItem();
        ResultSet misColumnas = this.getColumnas(this.paneTablTablModiComb.getSelectionModel().getSelectedItem());
        List<String> columnaNumber = new ArrayList<>();
        String llave1 = "";
        String llave2 = "";
        String queryFin = "";
        String id1 = "";
        String id2 = "";
        String Tabla = this.paneTablTablModiComb.getSelectionModel().getSelectedItem();
        ResultSet resultColumnas = this.getColumnas(this.paneTablTablModiComb.getSelectionModel().getSelectedItem());
        List<String> columnasTabla = new ArrayList<>();
        
       
        try {
            while(resultColumnas.next()){
                columnasTabla.add(resultColumnas.getString(1));
            }
            
            Statement sql = this.cx.createStatement();
            String query = "select ORDINAL_POSITION from information_schema.COLUMNS where TABLE_SCHEMA = '" + this.databaseConexion +"' AND TABLE_NAME = '" + Tabla + "' AND COLUMN_KEY = 'PRI';";
            ResultSet resultadoSQL = sql.executeQuery(query);
            
            while(resultadoSQL.next()){
                columnaNumber.add(resultadoSQL.getString(1));
            }
            
            llave1 = columnasTabla.get(Integer.parseInt(columnaNumber.get(0))-1);
            id1 = registro.getDato(Integer.parseInt(columnaNumber.get(0))-1);
            
            if(columnaNumber.size()==1){
                queryFin = "delete from " + Tabla + " where " + llave1 + " = '" + id1 + "';";
            }else if(columnaNumber.size()>=2){
                llave2 = columnasTabla.get(Integer.parseInt(columnaNumber.get(1))-1);
                id2 = registro.getDato(Integer.parseInt(columnaNumber.get(1))-1);
                queryFin = "delete from " + Tabla + " where " + llave1 + " = '" + id1 + "' and " + llave2 + " = '" + id2 + "';";
            }
            
            Statement sql2 = this.cx.createStatement();
            sql2.executeUpdate(queryFin);
            JOptionPane.showMessageDialog(null, "Se ha borrado el registro");
            this.paneTablTablModiTableView.getColumns().clear();
            this.creaTabla(this.paneTablTablModiComb.getSelectionModel().getSelectedItem());
            this.llenaTabla(this.getTodaTabla());
            
            
        } catch (SQLException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
   }
   
   private void modificarRegistro(){
       
        ObjetoDinamico registro = this.paneTablTablModiTableView.getSelectionModel().getSelectedItem();
        List<String> columnaNumber = new ArrayList<>();
        String llave1 = "";
        String llave2 = "";
        String queryFin = "";
        String id1 = "";
        String id2 = "";
        String Tabla = this.paneTablTablModiComb.getSelectionModel().getSelectedItem();
        ResultSet resultColumnas = this.getColumnas(this.paneTablTablModiComb.getSelectionModel().getSelectedItem());
        List<String> columnasTabla = new ArrayList<>();
       
        try {
            while(resultColumnas.next()){
                columnasTabla.add(resultColumnas.getString(1));
            }} catch (SQLException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        String old = JOptionPane.showInputDialog("Ingrese el nonbre de la columna del dato que le gustaria cambiar: ");
        String newData = JOptionPane.showInputDialog("Ingrese el nuevo valor del dato: ");
       
        try {
            Statement sql = this.cx.createStatement();
            String query = "select ORDINAL_POSITION from information_schema.COLUMNS where TABLE_SCHEMA = '" + this.databaseConexion +"' AND TABLE_NAME = '" + Tabla + "' AND COLUMN_KEY = 'PRI';";
            ResultSet resultadoSQL = sql.executeQuery(query);
            
            while(resultadoSQL.next()){
                columnaNumber.add(resultadoSQL.getString(1));
            }
            
            llave1 = columnasTabla.get(Integer.parseInt(columnaNumber.get(0))-1);
            id1 = registro.getDato(Integer.parseInt(columnaNumber.get(0))-1);
            
            if(columnaNumber.size()==1){
                queryFin = "update " + Tabla + " set " + old + " = '" + newData + "' where " + llave1 + " = '" + id1 + "';";
            }else if(columnaNumber.size()>=2){
                llave2 = columnasTabla.get(Integer.parseInt(columnaNumber.get(1))-1);
                id2 = registro.getDato(Integer.parseInt(columnaNumber.get(1))-1);
                queryFin = "update " + Tabla + " set " + old + " = '" + newData + "' where " + llave1 + " = '" + id1 + "' and " + llave2 + " = '" + id2 +"';";
            }
 
            
            
        } catch (SQLException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        try{
            Statement sql = this.cx.createStatement();
            sql.executeUpdate(queryFin);
            JOptionPane.showMessageDialog(null, "Se ha modificado " + old  + " a: "+ newData);
            this.paneTablTablModiTableView.getColumns().clear();
            this.creaTabla(Tabla);
            this.llenaTabla(this.getTodaTabla());
           
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
        
        }
   }
   
   private void nuevoRegistro(){
       ResultSet misColumnas = this.getColumnas(this.paneTablTablModiComb.getSelectionModel().getSelectedItem());
       String query = "INSERT INTO "+ this.paneTablTablModiComb.getSelectionModel().getSelectedItem()+" VALUES (";
       String dato;
       
       try {
           int t=0;
            while(misColumnas.next()){
                dato = JOptionPane.showInputDialog(null, "Ingrese el valor de: "+misColumnas.getString(1));
                if(t==0 && !dato.isBlank()){
                    query+= "'" + dato + "'";
                    t++;
                }else if(!dato.isBlank()){
                    query+= ", '" + dato + "' ";
                }else if(dato.isBlank()){
                    query+= ", NULL ";
                }  
            }} catch (SQLException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       query+= ");";
       
       this.actualizaQuery(query);
       this.llenaTabla(this.getTodaTabla());
   }
   
   
   
   
   //----------------------------  REGISTROS/ --------------------------------
   
   //---------------------------- /TABLAS -----------------------------------
   
   private void inspeccionarTabl(){
        this.paneTablTablModiTableView.getItems().clear();
        this.paneTablTablModiTableView.getColumns().clear();
        List<String> columnas = List.of("Field", "Type", "NULL", "Key", "Default", "Extra");
        ResultSet resultadoSQL = null;
        ObservableList<ObjetoDinamico> matriz = FXCollections.observableArrayList();
        List<String> registros = new ArrayList<>();
        String dato;
       
        for(int i = 0; i<6 ; i++){
            TableColumn<ObjetoDinamico, String> miColumna= new TableColumn<>(columnas.get(i));  
            this.paneTablTablModiTableView.getColumns().add(miColumna);
            miColumna.setCellValueFactory(new PropertyValueFactory<>(this.lasColumnas.get(i)));
            miColumna.setVisible(true);
        }
        
        Statement sql;
        try {
            sql = this.cx.createStatement();
            String query = "select COLUMN_NAME as 'Field', COLUMN_TYPE as 'Type', IS_NULLABLE as 'NULL', COLUMN_KEY as 'Key', COLUMN_DEFAULT as 'Default', EXTRA as 'Extra' from information_schema.COLUMNS where TABLE_SCHEMA = '" + this.databaseConexion +"' AND TABLE_NAME = '" + this.paneTablTablModiComb.getSelectionModel().getSelectedItem() + "';";
            resultadoSQL = sql.executeQuery(query);
            
            while(resultadoSQL.next()){
                registros.clear();
                
                for(int i = 0; i < 6; i++){
                    dato = resultadoSQL.getString(columnas.get(i));
                    registros.add(dato);
                }
               
                ObjetoDinamico obj = new ObjetoDinamico(columnas,registros);
                matriz.add(obj);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        this.paneTablTablModiTableView.getItems().clear();
        this.paneTablTablModiTableView.setItems(matriz);
        this.paneTablTablModiTableView.refresh();
        
        
   }
   
   private void llenadoComboTablas(ComboBox<String> comboBox){
       List<String> lista = new ArrayList<>();
       ResultSet result = null;
       lista.clear();
       
       try {       
            result = this.getTablas();
            
            while(result.next()){
                lista.add(result.getString(1));
            }
            result.close();
        } catch (SQLException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        ObservableList<String> namesTabl = FXCollections.observableArrayList(lista);
        comboBox.setItems(namesTabl);
        
   }
   
   private void llenadoListasTablas(){
       List<String> lista1 = new ArrayList<>();
       List<String> lista2 = new ArrayList<>();
       lista1.clear();
       lista2.clear();
       ResultSet result1 = null;
       ResultSet result2 = null;
       
       try {       
            result1 = this.getColumnas(this.paneConsCombTabl1.getSelectionModel().getSelectedItem());
            result2 = this.getColumnas(this.paneConsCombTabl2.getSelectionModel().getSelectedItem());
            
            while(result1.next()){
                lista1.add(result1.getString(1));
            }
            
            while(result2.next()){
                lista2.add(result2.getString(1));
            }
            result1.close();
            result2.close();
        } catch (SQLException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        ObservableList<String> namesCT1 = FXCollections.observableArrayList(lista1);
        this.paneConsListColmT1.setItems(namesCT1);
        ObservableList<String> namesCT2 = FXCollections.observableArrayList(lista2);
        this.paneConsListColmT2.setItems(namesCT2);
   }
   
   private ResultSet getTablas(){
        
        ResultSet resultado = null;
        
        try{
            Statement sql = this.cx.createStatement();
            String query = "show tables;";
            resultado = sql.executeQuery(query);
          
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
        
        }
        return resultado;
    }
   
   private void llenadoComboColumnas(){
       ResultSet resultado1 = null;
       ResultSet resultado2 = null;
       List<String> lista1 = new ArrayList<>();
       List<String> lista2 = new ArrayList<>();
       lista1.clear();
       lista2.clear();
        
        try{
            resultado1 = this.getColumnas(this.paneConsCombTabl1.getSelectionModel().getSelectedItem());
            while(resultado1.next()){
                lista1.add(resultado1.getString(1));
            }
            resultado1.close();
            resultado2 = this.getColumnas(this.paneConsCombTabl2.getSelectionModel().getSelectedItem());
            while(resultado2.next()){
                lista2.add(resultado2.getString(1));
            }
            resultado2.close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
        }
        ObservableList<String> relacion1 = FXCollections.observableArrayList(lista1);
        ObservableList<String> relacion2 = FXCollections.observableArrayList(lista2);
        
        this.paneConsCombRelaTabl1.setItems(relacion1);
        this.paneConsCombRelaTabl2.setItems(relacion2);
        this.paneConsCombCond1.setItems(relacion1);
        this.paneConsCombCond2.setItems(relacion2); 
        
        
        
   }
   
   
   private ResultSet getTodaTabla(){
       ResultSet resultadoTabla = null;
       
       if(this.paneTablTablModiComb.getSelectionModel().getSelectedItem() == null){
           this.paneTablTablModiComb.getSelectionModel().selectFirst();
       }
        try {
            Statement sql1 = this.cx.createStatement();
            String query1 = "select * from " + this.paneTablTablModiComb.getSelectionModel().getSelectedItem() + ";" ;
            resultadoTabla = sql1.executeQuery(query1);
        } catch (SQLException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return resultadoTabla;
   }
   
   private void creaTabla(String tablaColumnas){
       ResultSet Columnas = this.getColumnas(tablaColumnas);
       
        try {
            int t = 0;
            while(Columnas.next()){
                TableColumn<ObjetoDinamico, String> miColumna= new TableColumn<>(Columnas.getString(1));  
                this.paneTablTablModiTableView.getColumns().add(miColumna);
                miColumna.setCellValueFactory(new PropertyValueFactory<>(this.lasColumnas.get(t)));
                miColumna.setVisible(true);
                t++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   
   
   private ResultSet getColumnas(String tabla){
       ResultSet resultadoColumnas = null;
       
        try {
            Statement sql = this.cx.createStatement();
            String query = "select column_name as 'Field' FROM information_schema.COLUMNS WHERE TABLE_SCHEMA = '" + this.databaseConexion + "' AND TABLE_NAME = '" + tabla + "' order by ORDINAL_POSITION;" ;
            resultadoColumnas = sql.executeQuery(query);
            
        } catch (SQLException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return resultadoColumnas;
   }
   
   private void llenaTabla(ResultSet resultadoSQL){
       ObservableList<ObjetoDinamico> matriz = FXCollections.observableArrayList();
       List<String> registros = new ArrayList<>();
       String dato;
       ResultSet columnas;
       List<String> misColumnasList = new ArrayList<String>();
       ResultSet columnasObj = this.getColumnas(this.paneTablTablModiComb.getSelectionModel().getSelectedItem());    
       
        try {
            
            while(resultadoSQL.next()){
                registros.clear();
                
                columnas = this.getColumnas(this.paneTablTablModiComb.getSelectionModel().getSelectedItem());
                
                while(columnas.next()){
                    dato = resultadoSQL.getString(columnas.getString(1));
                    registros.add(dato);
                }
                

                while(columnasObj.next()){
                    misColumnasList.add(columnasObj.getString(1));
                }
            
                
                ObjetoDinamico obj = new ObjetoDinamico(misColumnasList,registros);
                matriz.add(obj);
                
            }
        
        } catch (SQLException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.paneTablTablModiTableView.getItems().clear();
        this.paneTablTablModiTableView.setItems(matriz);
        this.paneTablTablModiTableView.refresh();
   }
   
   private void eliminaTabla(){
       String query = "DROP TABLE "+this.paneTablTablModiComb.getSelectionModel().getSelectedItem()+";";
       this.actualizaQuery(query);
       this.llenadoComboTablas(this.paneTablTablModiComb);
   }
   
   private void creaNuevaTabla(){
       int t = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de columnas que desea para su tabla: "));
       String query = "CREATE TABLE " + JOptionPane.showInputDialog("Ingrese el nombre de la tabla: ") + " (";
       String dato = "";
       String tipo = "";
       
       for(int i=0;i<t;i++){
           if(i==0){
               dato = JOptionPane.showInputDialog(null, "Ingrese el nombre de la primera columna (LLave Primaria)");
               tipo = JOptionPane.showInputDialog(null, "Ingrese el tipo de dato de la LLave Primaria");
               query+= dato + " " + tipo + " PRIMARY KEY NOT NULL";
           }else{
               dato = JOptionPane.showInputDialog(null, "Ingrese el nombre de la siguiente columna");
               tipo = JOptionPane.showInputDialog(null, "Ingrese el tipo de dato de la columna");
               query+= ", " +dato + " " + tipo;
           }
       }
       
       query+=");";
       
       this.actualizaQuery(query);
       this.llenadoComboTablas(this.paneTablTablModiComb);
       
   }
   
   private void modificaTabla(){
       String tabla = this.paneTablTablModiComb.getSelectionModel().getSelectedItem();
       String modificacion = "";
       modificacion+= this.paneTablTablModiCombAlter.getSelectionModel().getSelectedItem();
       String query = "ALTER TABLE "+tabla+ " "+ modificacion;
       String col1 = "";
       String col2 = "";
       String type = "";
      
       if(!modificacion.isEmpty()){
           switch (modificacion) {
                case "add" -> {
                    col1 = JOptionPane.showInputDialog("Ingrese la columna a añadir: ");
                    type = JOptionPane.showInputDialog(null, "Ingrese el tipo de dato para la columna: ");
                    query+= " "+col1+" "+type+";";  
                    this.actualizaQuery(query);
                    this.paneTablTablModiTableView.getColumns().clear();
                    this.creaTabla(this.paneTablTablModiComb.getSelectionModel().getSelectedItem());
                    this.llenaTabla(this.getTodaTabla());
                    
                }
                case "change" -> {
                    col1 = JOptionPane.showInputDialog("Ingrese la columna que desea reemplazar: ");
                    col2 = JOptionPane.showInputDialog("Ingrese la nueva columna: ");
                    type = JOptionPane.showInputDialog(null, "Ingrese el tipo de dato para la columna: ");
                    query+= " "+col1+" "+ col2 +" "+type+";";    
                    this.actualizaQuery(query);
                    this.paneTablTablModiTableView.getColumns().clear();
                    this.creaTabla(this.paneTablTablModiComb.getSelectionModel().getSelectedItem());
                    this.llenaTabla(this.getTodaTabla());
                }
                case "modify" -> {
                    col1 = JOptionPane.showInputDialog("Ingrese la columna a modificar: ");
                    type = JOptionPane.showInputDialog(null, "Ingrese el nuevo tipo de dato para la columna: ");
                    query+= " "+col1+" "+type+";";    
                    this.actualizaQuery(query);
                    this.paneTablTablModiTableView.getColumns().clear();
                    this.creaTabla(this.paneTablTablModiComb.getSelectionModel().getSelectedItem());
                    this.llenaTabla(this.getTodaTabla());
                }
                case "drop" -> {
                    col1 = JOptionPane.showInputDialog("Ingrese la columna que sea eliminar: ");
                    query+= " "+col1+";";  
                    this.actualizaQuery(query);
                    this.paneTablTablModiTableView.getColumns().clear();
                    this.creaTabla(this.paneTablTablModiComb.getSelectionModel().getSelectedItem());
                    this.llenaTabla(this.getTodaTabla());
                }
                default -> {
                    JOptionPane.showMessageDialog(null, "¡Debe escoger el tipo de modificacion con el comboBOX!");
                }
            }
       
       }  
  
        
        
        
  
   }
   
   
   //---------------------------- TABLAS/ ----------------------------------- 
   
   //------------------------ /Conexiones ----------------------------
    
    private Connection conectarPrimario(){
        
        try {
            Class.forName(this.driver);
            this.cx = DriverManager.getConnection("jdbc:mysql://" + this.rutaConexion + ":" + this.puertoConexion + "/" + this.databaseConexion, this.userConexion, this.passwordConexion);
            System.out.println("SE CONECTO A LA BASE: " + this.databaseConexion);
            JOptionPane.showMessageDialog(null, "Se conecto a la base de datos: " + this.databaseConexion);
            this.menuBaseDato.setDisable(false);
            this.menuCons.setDisable(false);
            this.menuTabl.setDisable(false);
            this.paneBaseDato.setVisible(true);
            this.paneBaseDato.toFront();
            this.llenadoTablaBaseDato();
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("NO SE CONECTO A LA BASE" + this.databaseConexion);
            JOptionPane.showMessageDialog(null, "No se conecto a la base de datos: " + this.databaseConexion);
        }
        return this.cx;
    }
    
    private Connection conectarSecundario(){
        
        try {
            Class.forName(this.driver);
            this.cx = DriverManager.getConnection("jdbc:mysql://" + this.rutaConexion + ":" + this.puertoConexion + "/" + this.databaseConexion, this.userConexion, this.passwordConexion);
            System.out.println("SE CONECTO A LA BASE: " + this.databaseConexion);
            JOptionPane.showMessageDialog(null, "Se conecto a la base de datos: " + this.databaseConexion);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("NO SE CONECTO A LA BASE" + this.databaseConexion);
            JOptionPane.showMessageDialog(null, "No se conecto a la base de datos: " + this.databaseConexion);
        }
        return this.cx;
    }
    
    public void desconectar(){
        
        try {
            this.cx.close();
        } catch (SQLException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
     //------------------------ Conexiones/ ----------------------------
    
    //------------------------ /Consultas -----------------------------
    private void llenarComboOperadores(){
        this.paneConsCombOper1.getItems().add("LIKE");
        this.paneConsCombOper1.getItems().add("NOT LIKE");
        this.paneConsCombOper1.getItems().add("IS NULL");
        this.paneConsCombOper1.getItems().add("IS NOT NULL");
        this.paneConsCombOper1.getItems().add("<");
        this.paneConsCombOper1.getItems().add(">");
        this.paneConsCombOper1.getItems().add(">=");
        this.paneConsCombOper1.getItems().add("<=");
        this.paneConsCombOper1.getItems().add("=");
        this.paneConsCombOper1.getItems().add("<>");
        
        this.paneConsCombOper2.getItems().add("LIKE");
        this.paneConsCombOper2.getItems().add("NOT LIKE");
        this.paneConsCombOper2.getItems().add("IS NULL");
        this.paneConsCombOper2.getItems().add("IS NOT NULL");
        this.paneConsCombOper2.getItems().add("<");
        this.paneConsCombOper2.getItems().add(">");
        this.paneConsCombOper2.getItems().add(">=");
        this.paneConsCombOper2.getItems().add("<=");
        this.paneConsCombOper2.getItems().add("=");
        this.paneConsCombOper2.getItems().add("<>");
    }
    
    private String construyeConsulta(){
        String consulta = "select ";
        String tabla1 = this.paneConsCombTabl1.getSelectionModel().getSelectedItem();
        String tabla2 = this.paneConsCombTabl2.getSelectionModel().getSelectedItem();
        Boolean condi1 = this.paneConsChec1.isSelected();
        Boolean condi2 = this.paneConsChec2.isSelected();
        String campo1 = this.paneConsCombCond1.getSelectionModel().getSelectedItem();
        String campo2 = this.paneConsCombCond2.getSelectionModel().getSelectedItem();
        String operador1 = this.paneConsCombOper1.getSelectionModel().getSelectedItem();
        String operador2 = this.paneConsCombOper2.getSelectionModel().getSelectedItem();
        String valor1 = this.paneConsTextValo1.getText();
        String valor2 = this.paneConsTextValo2.getText();
        
        if(!this.listaColumnasCons.isEmpty()){
            int t = 0;
            for(String miColumna: this.listaColumnasCons){
                
                if(t == 0){
                    consulta+= miColumna;
                }else{
                    consulta+= " , " + miColumna;
                }
                t++;
            }
            
            consulta+= " from " + tabla1 + " , " + tabla2 + " where " + tabla1 + "." + this.paneConsCombRelaTabl1.getSelectionModel().getSelectedItem() + " = " + tabla2 + "." + this.paneConsCombRelaTabl2.getSelectionModel().getSelectedItem();
            
            
            
            if ( condi1 && condi2 && !valor1.isEmpty() && !valor2.isEmpty()){
                consulta+= " and " + tabla1 +"." + campo1  +" "+ operador1 + " '" + valor1 + "' " + " and " + tabla2 +"."+campo2 +" "+ operador2 + " '" + valor2 + "' ;";
            }else if(condi1 && !condi2 && !valor1.isEmpty()){
                consulta+= " and " + tabla1 +"."+campo1 +" "+ operador1 + " '" + valor1 + "' ;";
            }else if(!condi1 && condi2 && !valor2.isEmpty()){
                consulta+= " and " + tabla2 +"."+campo2 +" "+ operador2 + " '" + valor2 + "' ;";
            }else if(condi1 && !condi2 && valor1.isEmpty()){
                consulta+= " and " + tabla1 +"."+campo1 +" "+ operador1 + " ;";
            }else if(!condi1 && condi2 && valor2.isEmpty()){
                consulta+= " and " + tabla2 +"."+campo2 +" "+ operador2 + " ;";
            }else if(condi1 && condi2 && valor1.isEmpty() && !valor2.isEmpty()){
                consulta+= " and " + tabla1 +"."+campo1 +" "+ operador1 + " and " + tabla2 +"."+campo2 +" "+ operador2 + " '" + valor2 + "' ;";
            }else if (condi1 && condi2 && !valor1.isEmpty() && valor2.isEmpty()){
                consulta+= " and " + tabla1 +"."+campo1 +" "+ operador1 + " '" + valor1 + "' " + " and " + tabla2 +"."+campo2 +" "+ operador2 + " ;";
            }else if ( condi1 && condi2 && valor1.isEmpty() && valor2.isEmpty()){
                consulta+= " and " + tabla1 +"."+campo1 +" "+ operador1 + " and " + tabla2 +"."+campo2 +" "+ operador2 +  " ;";
            }
            
            JOptionPane.showMessageDialog(null, consulta);
        }else{
            JOptionPane.showMessageDialog(null, "Debe seleccionar por lo menos una columna para la tabla resultado");
        }
        
        return consulta;
    }
    
    private void añadeColumna(String columnaNueva){
       TableColumn<ObjetoDinamico, String> miColumna= new TableColumn<>(columnaNueva);   
       this.paneConsTablView.getColumns().add(miColumna);
       miColumna.setCellValueFactory(new PropertyValueFactory<>(this.lasColumnas.get(this.numeroColumnasConsulta)));
       this.numeroColumnasConsulta++;
       
       miColumna.setCellFactory(TextFieldTableCell.forTableColumn());
       miColumna.setOnEditCommit(    
               new EventHandler<CellEditEvent<ObjetoDinamico, String>>() {
               @Override        
               public void handle(CellEditEvent<ObjetoDinamico, String> t) {
                   ((ObjetoDinamico) t.getTableView().getItems().get(t.getTablePosition().getRow())
                   ).setA(t.getNewValue());
       }    });
   }
    
    private void limpiarConsulta(){
        this.listaColumnasCons.clear();
        this.paneConsTablView.getColumns().clear();
        this.paneConsTablView.getItems().clear();
        this.numeroColumnasConsulta = 0;
    }
    
    private void realizaConsulta(){
       String query = this.construyeConsulta();
       ResultSet resultadoSQL = null;   
       ObservableList<ObjetoDinamico> matriz = FXCollections.observableArrayList();
       List<String> registros = new ArrayList<>();
       String dato;
       
          
        try {
            
            Statement sql = this.cx.createStatement();
            resultadoSQL = sql.executeQuery(query);
            
            while(resultadoSQL.next()){
                registros.clear();
               
                for(String columnaResul: this.listaColumnasCons){
                    dato = resultadoSQL.getString(columnaResul);
                    registros.add(dato);
                }
                
                ObjetoDinamico obj = new ObjetoDinamico(this.listaColumnasCons,registros);
                matriz.add(obj);
            }
        
        } catch (SQLException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.paneConsTablView.getItems().clear();
        this.paneConsTablView.setItems(matriz);
        this.paneConsTablView.refresh();
        
    }
    
    private void creaVista(){
        String nombreVista = JOptionPane.showInputDialog(null, "Ingrese el nombre para la Vista: ");
        String query = "CREATE VIEW "+ nombreVista +" AS "+this.construyeConsulta();
         this.actualizaQuery(query);
    }
    
    private void eliminarRegistroConsulta(){
        ObjetoDinamico obj = this.paneConsTablView.getSelectionModel().getSelectedItem();
        this.paneConsTablView.getItems().remove(obj);
        this.paneConsTablView.refresh();
    }
    
    private void eliminaVista(){
        String query = "DROP VIEW IF EXISTS "+this.paneTablTablModiComb.getSelectionModel().getSelectedItem()+";";
        this.actualizaQuery(query);
        this.llenadoComboTablas(this.paneTablTablModiComb);
    }
    
    private ResultSet realizaQuery(String query){
        
        ResultSet resultadoSQL = null;
        Statement sql;
        try {
            sql = this.cx.createStatement();
            resultadoSQL = sql.executeQuery(query);
            sql.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return resultadoSQL;
    }
    
    private void actualizaQuery(String query){
        
        ResultSet resultadoSQL = null;
        Statement sql;
        try {
            sql = this.cx.createStatement();
            sql.executeUpdate(query);
            sql.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    
     //------------------------ Consultas/ -----------------------------

    

    

    

    
 
    

    
    
}
