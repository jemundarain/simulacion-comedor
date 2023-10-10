package com.company;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import java.util.Vector;

public class ControllerDatos implements Initializable{

        /*Columnas*/
        public TableColumn<Tiempo, Integer> column_TiempoEntreLlegadas = new TableColumn<>();
        public TableColumn<Tiempo, Double> column_ProbEntreLlegadas = new TableColumn<>();

        public TableColumn<Tiempo, Integer> column_TiempoServicios1 = new TableColumn<>();
        public TableColumn<Tiempo, Double> column_ProbServicios1 = new TableColumn<>();

        public TableColumn<Tiempo, Integer> column_TiempoServicios2 = new TableColumn<>();
        public TableColumn<Tiempo, Double> column_ProbServicios2 = new TableColumn<>();

        public TableColumn<Tiempo, Integer> column_TiempoServicios3 = new TableColumn<>();
        public TableColumn<Tiempo, Double> column_ProbServicios3 = new TableColumn<>();

        public TableColumn<Tiempo, Integer> column_TiempoServicios4 = new TableColumn<>();
        public TableColumn<Tiempo, Double> column_ProbServicios4 = new TableColumn<>();

        /*Elementos*/
        public Button btn_FileChooser = new Button();
        public Label lab_SingleFile = new Label();
        public TextField tf_TiempoLlegada = new TextField();
        public TextField tf_ProbTiempoLlegada = new TextField();
        public TextField tf_Nombre = new TextField();
        public TextField tf_Dias = new TextField();
        public TextField tf_CantServ1 = new TextField();
        public TextField tf_CantServ2 = new TextField();
        public TextField tf_CantServ3 = new TextField();
        public TextField tf_CantServ4 = new TextField();
        public Button btn_AddTiempoLlegada = new Button();
        public TextField tf_TiempoServicio1 = new TextField();
        public TextField tf_TiempoServicio2 = new TextField();
        public TextField tf_TiempoServicio3 = new TextField();
        public TextField tf_TiempoServicio4 = new TextField();
        public TextField tf_Prob1 = new TextField();
        public TextField tf_Prob2 = new TextField();
        public TextField tf_Prob3 = new TextField();
        public TextField tf_Prob4 = new TextField();
        public Button btn_AddTiempoServicio1 = new Button();
        public Button btn_AddTiempoServicio2 = new Button();
        public Button btn_AddTiempoServicio3 = new Button();
        public Button btn_AddTiempoServicio4 = new Button();
        public Button btn_DeleteTiempoLlegada = new Button();
        public Button btn_DeleteTiempoServicio1 = new Button();
        public Button btn_DeleteTiempoServicio2 = new Button();
        public Button btn_DeleteTiempoServicio3 = new Button();
        public Button btn_DeleteTiempoServicio4 = new Button();
        public Button btn_Simular = new Button();

        /*Tabla*/
        public TableView<Tiempo> table_EntreLlegadas = new TableView<>();
        public TableView<Tiempo> table_Servicios1 = new TableView<>();
        public TableView<Tiempo> table_Servicios2 = new TableView<>();
        public TableView<Tiempo> table_Servicios3 = new TableView<>();
        public TableView<Tiempo> table_Servicios4 = new TableView<>();
        public RadioButton radiob_CargarSimulacion = new RadioButton();
        public RadioButton radiob_CrearSimulacion = new RadioButton();

        /*Botones*/
        public Button btn_Ver = new Button();
        public Button btn_Limpiar = new Button();
        public Button btn_Atras = new Button();

        public AnchorPane ventanaDatos = new AnchorPane();
        public Simulacion comedor = new Simulacion();

    public  void display(String title, String message){
            Stage window = new Stage();

            window.initModality(Modality.APPLICATION_MODAL);
            window.setTitle(title);
            window.setMinWidth(250);

            Label lab = new Label();
            lab.setText(message);
            Button btn = new Button("Aceptar");
            btn.setOnAction(e -> window.close());

            VBox layout = new VBox(10);
            layout.getChildren().addAll(lab, btn);
            layout.setAlignment(Pos.CENTER);

            Scene scene = new Scene(layout);
            window.setScene(scene);
            window.showAndWait();

    }

        public void singleFileChooser(ActionEvent Event){
            FileChooser fc = new FileChooser();
            fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
            File f = fc.showOpenDialog(null);

            if(f != null)
                lab_SingleFile.setText(f.getAbsolutePath());
        }

        public void addTiempo(TableColumn column_Tiempo, TableColumn column_Prob, TextField tf_Tiempo, TextField tf_Prob, TableView<Tiempo> table){
            /*variables auxiliares*/
            Float suma= new Float(0);
            boolean repetido = false;
            Tiempo tiempo = new Tiempo();

            /*inicialización de columnas*/
            column_Tiempo.setCellValueFactory(new PropertyValueFactory<>("tiempo"));
            column_Prob.setCellValueFactory(new PropertyValueFactory<>("probabilidad"));
            tiempo.setTiempo(Integer.parseInt(tf_Tiempo.getText()));
            tiempo.setProbabilidad(Float.parseFloat(tf_Prob.getText()));

            for(int i=0 ; i < table.getItems().size()  ; i++) {
                suma += table.getItems().get(i).getProbabilidad();
                if(table.getItems().get(i).getTiempo() == tiempo.getTiempo()) {
                    repetido = true;
                }
            }
            suma += tiempo.getProbabilidad();
            //hacer una excepcion?
            if(suma>100) {
                display("Error", "Las probabilidades deben sumar 100%");
            }else{
                if(repetido==true) {
                    display("Error","Tiempo entre llegadas repetido");
                }else table.getItems().add(tiempo);
            }
            tf_Tiempo.clear();
            tf_Prob.clear();
        }

        public void addTiempoLlegada(){
            addTiempo(column_TiempoEntreLlegadas, column_ProbEntreLlegadas, tf_TiempoLlegada, tf_ProbTiempoLlegada, table_EntreLlegadas);
        }

        public void addTiempoServicio1(){
            addTiempo(column_TiempoServicios1, column_ProbServicios1, tf_TiempoServicio1, tf_Prob1, table_Servicios1);
        }

        public void addTiempoServicio2(){
            addTiempo(column_TiempoServicios2, column_ProbServicios2, tf_TiempoServicio2, tf_Prob2, table_Servicios2);
        }

        public void addTiempoServicio3(){
            addTiempo(column_TiempoServicios3, column_ProbServicios3, tf_TiempoServicio3, tf_Prob3, table_Servicios3);
        }

        public void addTiempoServicio4(){
            addTiempo(column_TiempoServicios4, column_ProbServicios4, tf_TiempoServicio4, tf_Prob4, table_Servicios4);
        }

        public void deleteTiempo(TableView<Tiempo> table){
            ObservableList<Tiempo> tiempoSeleccionado, todosTiempos;
            todosTiempos = table.getItems();
            tiempoSeleccionado = table.getSelectionModel().getSelectedItems();
            tiempoSeleccionado.forEach(todosTiempos::remove);
        }

        public void deleteTiempoLlegada(){
            deleteTiempo(table_EntreLlegadas);
        }

        public void deleteTiempoServicio1(){
            deleteTiempo(table_Servicios1);
        }

        public void deleteTiempoServicio2(){
            deleteTiempo(table_Servicios2);
        }

        public void deleteTiempoServicio3(){
            deleteTiempo(table_Servicios3);
        }

        public void deleteTiempoServicio4(){
            deleteTiempo(table_Servicios4);
        }



        public void selectRadio(ActionEvent Event){
            if(radiob_CrearSimulacion.isSelected()) {
                deshabilitar(false);
                btn_FileChooser.setDisable(true);
                btn_Ver.setDisable(true);
            }else{
                deshabilitar(true);
                btn_FileChooser.setDisable(false);
                btn_Ver.setDisable(false);
            }

        }

        public void leerSimulacion(String archivo) throws FileNotFoundException, IOException {
            DecimalFormat df = new DecimalFormat("#.##");
            String cadena;
            FileReader f = new FileReader(archivo);
            BufferedReader b = new BufferedReader(f);
            tf_Nombre.setText(b.readLine());
            tf_Dias.setText(b.readLine());
            tf_CantServ1.setText(b.readLine());
            tf_CantServ2.setText(b.readLine());
            tf_CantServ3.setText(b.readLine());
            tf_CantServ4.setText(b.readLine());

            column_TiempoEntreLlegadas.setCellValueFactory(new PropertyValueFactory<>("tiempo"));
            column_ProbEntreLlegadas.setCellValueFactory(new PropertyValueFactory<>("probabilidad"));

            int cant_tiempos = Integer.parseInt(b.readLine()), i;
            for(i=0 ; i < cant_tiempos ; i++) {
                Tiempo time = new Tiempo();
                time.setTiempo(Integer.parseInt(b.readLine()));
                time.setProbabilidad(Float.parseFloat(df.format(Float.parseFloat(b.readLine())*100)));
                table_EntreLlegadas.getItems().add(time);
            }

            column_TiempoServicios1.setCellValueFactory(new PropertyValueFactory<>("tiempo"));
            column_ProbServicios1.setCellValueFactory(new PropertyValueFactory<>("probabilidad"));

            cant_tiempos = Integer.parseInt(b.readLine());
            for(i=0 ; i < cant_tiempos ; i++) {
                Tiempo time = new Tiempo();
                time.setTiempo(Integer.parseInt(b.readLine()));
                time.setProbabilidad(Float.parseFloat(df.format(Float.parseFloat(b.readLine())*100)));
                table_Servicios1.getItems().add(time);
            }

            column_TiempoServicios2.setCellValueFactory(new PropertyValueFactory<>("tiempo"));
            column_ProbServicios2.setCellValueFactory(new PropertyValueFactory<>("probabilidad"));

            cant_tiempos = Integer.parseInt(b.readLine());
            for(i=0 ; i < cant_tiempos ; i++) {
                Tiempo time = new Tiempo();
                time.setTiempo(Integer.parseInt(b.readLine()));
                time.setProbabilidad(Float.parseFloat(df.format(Float.parseFloat(b.readLine())*100)));
                table_Servicios2.getItems().add(time);
            }

            column_TiempoServicios3.setCellValueFactory(new PropertyValueFactory<>("tiempo"));
            column_ProbServicios3.setCellValueFactory(new PropertyValueFactory<>("probabilidad"));

            cant_tiempos = Integer.parseInt(b.readLine());
            for(i=0 ; i < cant_tiempos ; i++) {
                Tiempo time = new Tiempo();
                time.setTiempo(Integer.parseInt(b.readLine()));
                time.setProbabilidad(Float.parseFloat(df.format(Float.parseFloat(b.readLine())*100)));
                table_Servicios3.getItems().add(time);
            }

            column_TiempoServicios4.setCellValueFactory(new PropertyValueFactory<>("tiempo"));
            column_ProbServicios4.setCellValueFactory(new PropertyValueFactory<>("probabilidad"));

            cant_tiempos = Integer.parseInt(b.readLine());
            for(i=0 ; i < cant_tiempos ; i++) {
                Tiempo time = new Tiempo();
                time.setTiempo(Integer.parseInt(b.readLine()));
                time.setProbabilidad(Float.parseFloat(df.format(Float.parseFloat(b.readLine())*100)));
                table_Servicios4.getItems().add(time);
            }

            b.close();
        }

        public void cargarSimulacion() throws FileNotFoundException, IOException{
            int i;
            comedor.setNombre(tf_Nombre.getText());
            comedor.setDias(Integer.parseInt(tf_Dias.getText()));
            comedor.setMX(Integer.parseInt(tf_Dias.getText())*180); //*dias * jornadas de 3 horas * 60 min
            for(i=0 ; i<4 ; i++) {
                comedor.getEtapas().add(new Etapa());
            }

            for(i=0 ; i<Integer.parseInt(tf_CantServ1.getText()) ; i++){
                comedor.getEtapas().get(0).getServidores().add(new Servidor());
            }
            for(i=0 ; i<Integer.parseInt(tf_CantServ2.getText()) ; i++){
                comedor.getEtapas().get(1).getServidores().add(new Servidor());
            }
            for(i=0 ; i<Integer.parseInt(tf_CantServ3.getText()) ; i++){
                comedor.getEtapas().get(2).getServidores().add(new Servidor());
            }
            for(i=0 ; i<Integer.parseInt(tf_CantServ4.getText()) ; i++){
                comedor.getEtapas().get(3).getServidores().add(new Servidor());
            }

            //anadir tiempos entre llegadas al sistema
            for( i=0 ; i<table_EntreLlegadas.getItems().size() ; i++){
                comedor.getEntreLlegadas().add(table_EntreLlegadas.getItems().get(i));
            }

            //anadir tiempos de servicio a la etapa 1
            for( i=0 ; i<table_Servicios1.getItems().size() ; i++){
                comedor.getEtapas().get(0).deServicios.add(table_Servicios1.getItems().get(i));
            }

            //anadir tiempos de servicio a la etapa 2
            for( i=0 ; i<table_Servicios2.getItems().size() ; i++){
                comedor.getEtapas().get(1).deServicios.add(table_Servicios2.getItems().get(i));
            }

            //anadir tiempos de servicio a la etapa 3
            for( i=0 ; i<table_Servicios3.getItems().size() ; i++){
                comedor.getEtapas().get(2).deServicios.add(table_Servicios3.getItems().get(i));
            }

            //anadir tiempos de servicio a la etapa 4
            for( i=0 ; i<table_Servicios4.getItems().size() ; i++){
                comedor.getEtapas().get(3).deServicios.add(table_Servicios4.getItems().get(i));
            }

        }

        public Cliente clienteEstacionAnterior(Integer estacionAnterior){
            for(int i=0 ; i < comedor.getEtapas().get(estacionAnterior).getServidores().size() ; i++){
                //cliente con el menor tiempo de servicio
            }
            return new Cliente();
        }

        public void abrirComedor() throws IOException {
            cargarSimulacion();
            comedor.generarTiemposEntreLlegadas();
            for(int i=0 ; i<4 ; i++) {
                comedor.getEtapas().get(i).generarTiemposDeServicio();
            }

            while(comedor.TM <= comedor.MX) {
                if (comedor.getProximoEvento().getTipo() == "llegada") {
                    comedor.TM += comedor.getProximoEvento().getTiempo();
                    int servidorDisponible = comedor.getEtapas().get(comedor.proximoEvento.getEstacion()).buscarServidorDisponible();
                    if (servidorDisponible != -1) {
                        int tiempoServicio = comedor.getEtapas().get(comedor.proximoEvento.getEstacion()).getTiempoDeServicio();
                        comedor.getEtapas().get(comedor.proximoEvento.getEstacion()).DT = comedor.getTM() + tiempoServicio;
                        comedor.getEtapas().get(comedor.proximoEvento.getEstacion()).getServidores().get(servidorDisponible).setSS(true);
                        if(comedor.proximoEvento.getEstacion()==1) {
                            comedor.getEtapas().get(comedor.proximoEvento.getEstacion()).getServidores().get(servidorDisponible).setCliente(new Cliente(++comedor.numClientes, tiempoServicio));
                        }else{
                            comedor.getEtapas().get(comedor.proximoEvento.getEstacion()).getServidores().get(servidorDisponible).setCliente(clienteEstacionAnterior(comedor.proximoEvento.getEstacion()));
                            //desocupar servidor getEstacion--
                        }
                    } else {
                        comedor.etapas.get(comedor.proximoEvento.getEstacion()).WL++;
                        comedor.etapas.get(comedor.proximoEvento.getEstacion()).AT = comedor.TM + comedor.getTiempoEntreLlegada();
                        if(comedor.proximoEvento.getEstacion()==1){
                            comedor.getEtapas().get(comedor.proximoEvento.getEstacion()).getCola().add(new Cliente(++comedor.numClientes));
                        }else {
                            //comedor.getEtapas().get(comedor.proximoEvento.getEstacion()).getCola().add(CLIENTE CON MENOR TIEMPO DE SERICIO ESTACION ANTERIRO);
                            //desocupar servidor getEstacion--
                        }
                    }
                } else {
                    //salida
                    comedor.TM += comedor.proximoEvento.getTiempo();
                    if (comedor.etapas.get(comedor.proximoEvento.getEstacion()).WL > 0) {
                        comedor.etapas.get(comedor.proximoEvento.getEstacion()).WL--;
                        //saco del servidor
                        //el primero de la cola pasa a servicio
                        if(comedor.proximoEvento.getEstacion()!=4){
                            //ocupo cola/servidor estacion++
                        }
                    } else {
                        comedor.etapas.get(comedor.proximoEvento.getEstacion()).servidores.get(1).SS = false;//desoupo servidor que corresponda
                        comedor.etapas.get(comedor.proximoEvento.getEstacion()).DT = comedor.MX + 1;
                    }
                }
                comedor.actualizarProximoEvento();
            }
        }

        public void simular() throws IOException {
            if(radiob_CargarSimulacion.isSelected()) {
                if (!lab_SingleFile.getText().isEmpty()) {
                    ver();
                    abrirComedor();
                } else {
                    display("Error", "Selecione un archivo de simulación");
                }
            }else{
                abrirComedor();
            }
        }

        public void limpiar(){
            tf_Nombre.clear();
            tf_Dias.clear();
            tf_TiempoLlegada.clear();
            tf_ProbTiempoLlegada.clear();
            tf_CantServ1.clear();
            tf_CantServ2.clear();
            tf_CantServ3.clear();
            tf_CantServ4.clear();
            tf_TiempoServicio1.clear();
            tf_TiempoServicio2.clear();
            tf_TiempoServicio3.clear();
            tf_TiempoServicio4.clear();
            tf_Prob1.clear();
            tf_Prob2.clear();
            tf_Prob3.clear();
            tf_Prob4.clear();
            table_EntreLlegadas.getItems().clear();
            table_Servicios1.getItems().clear();
            table_Servicios2.getItems().clear();
            table_Servicios3.getItems().clear();
            table_Servicios4.getItems().clear();
        }

        public void ver() throws IOException {
            table_EntreLlegadas.getItems().clear();
            table_Servicios1.getItems().clear();
            table_Servicios2.getItems().clear();
            table_Servicios3.getItems().clear();
            table_Servicios4.getItems().clear();
            leerSimulacion(lab_SingleFile.getText());
        }

        public void deshabilitar(boolean bool) {
            tf_Nombre.setDisable(bool);
            tf_ProbTiempoLlegada.setDisable(bool);
            tf_Dias.setDisable(bool);
            tf_TiempoLlegada.setDisable(bool);
            tf_ProbTiempoLlegada.setDisable(bool);
            btn_AddTiempoLlegada.setDisable(bool);
            tf_CantServ1.setDisable(bool);
            tf_CantServ2.setDisable(bool);
            tf_CantServ3.setDisable(bool);
            tf_CantServ4.setDisable(bool);
            tf_TiempoServicio1.setDisable(bool);
            tf_TiempoServicio2.setDisable(bool);
            tf_TiempoServicio3.setDisable(bool);
            tf_TiempoServicio4.setDisable(bool);
            btn_AddTiempoServicio1.setDisable(bool);
            btn_AddTiempoServicio2.setDisable(bool);
            btn_AddTiempoServicio3.setDisable(bool);
            btn_AddTiempoServicio4.setDisable(bool);
            tf_Prob1.setDisable(bool);
            tf_Prob2.setDisable(bool);
            tf_Prob3.setDisable(bool);
            tf_Prob4.setDisable(bool);
            btn_DeleteTiempoLlegada.setDisable(bool);
            btn_DeleteTiempoServicio1.setDisable(bool);
            btn_DeleteTiempoServicio2.setDisable(bool);
            btn_DeleteTiempoServicio3.setDisable(bool);
            btn_DeleteTiempoServicio4.setDisable(bool);
            table_EntreLlegadas.setDisable(bool);
            table_Servicios1.setDisable(bool);
            table_Servicios2.setDisable(bool);
            table_Servicios3.setDisable(bool);
            table_Servicios4.setDisable(bool);
        }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        deshabilitar(true);
        tf_TiempoLlegada.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    tf_TiempoLlegada.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        tf_CantServ1.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    tf_CantServ1.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        tf_CantServ2.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    tf_CantServ2.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        tf_CantServ3.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    tf_CantServ3.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        tf_CantServ1.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    tf_CantServ1.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        tf_CantServ4.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    tf_CantServ4.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        tf_TiempoServicio1.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    tf_TiempoServicio1.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        tf_TiempoServicio2.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    tf_TiempoServicio2.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        tf_TiempoServicio3.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    tf_TiempoServicio3.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        tf_TiempoServicio4.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    tf_TiempoServicio4.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        tf_Dias.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    tf_Dias.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }
}
