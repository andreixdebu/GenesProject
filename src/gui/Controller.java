package gui;

import domain.Genes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import service.Service;

import java.util.Comparator;
import java.util.Objects;
import java.util.Vector;
import java.util.stream.Collectors;

public class Controller {
    private Service service;

    @FXML
    private ListView<Genes> listView = new ListView<>();

    @FXML
    private ComboBox<String> comboBox = new ComboBox<>();

    @FXML
    private TextField t1;

    @FXML
    private TextField selectShow;

    @FXML
    private TextArea areaSelectShow;

    @FXML
    private TextField compareSequences;

    @FXML
    private ListView<String> pointMutationsListView = new ListView<>();

    public Controller(Service service) {
        this.service = service;
    }

    public void initialize(){
        service.openConnection();
        service.createSchema();
        //service.addInSchema();

        ObservableList<Genes> listObs = FXCollections.observableArrayList();

        Comparator<Genes> compareGenes = Comparator
                .comparing(Genes::getOrganism);

        Vector<Genes> routesSourted = service.getAll().stream()
                .sorted(compareGenes)
                .collect(Collectors.toCollection(Vector::new));

        for(Genes g:routesSourted)
            listObs.add(g);

        listView.setItems(listObs);

        ObservableList<String> listCombo = FXCollections.observableArrayList();
        Vector<String> vector = new Vector<>();

        for (Genes g:service.getAll()){
            if(g.getOrganism()!=null){
                String[] parts = g.getOrganism().split("/");
                vector.add(parts[0]);
            }
        }
        for(String s:vector)
            if(!listCombo.contains(s))
                listCombo.add(s);
        comboBox.setItems(listCombo);
    }

    @FXML
    void seeAfterCombo(ActionEvent event){

        ObservableList<Genes> listObs = FXCollections.observableArrayList();
        Vector<Genes> vec = new Vector<Genes>();

        for(Genes g: service.getAll())
            if(g.getOrganism()!=null)
                vec.add(g);

        Comparator<Genes> compareGenes = Comparator
                .comparing(Genes::getOrganism);

        Vector<Genes> genesSorted = vec.stream()
                .sorted(compareGenes)
                .collect(Collectors.toCollection(Vector::new));

        Vector<Genes> genesFiltered = genesSorted.stream()
                .filter(g1->g1.getOrganism().contains(comboBox.getSelectionModel().getSelectedItem())
                && (g1.getName().contains(t1.getText()) || g1.getFunction().contains(t1.getText())))
                .collect(Collectors.toCollection(Vector::new));

        for(Genes g:genesFiltered)
            listObs.add(g);

        listView.setItems(listObs);

    }

    @FXML
    void seeAfterSelect(MouseEvent event)
    {
        listView.getSelectionModel().getSelectedItems().toString();
        String[] parts = listView.getSelectionModel().getSelectedItem().toString().split(" / ");
        areaSelectShow.setText(parts[2]);
        selectShow.setText(parts[3]);
    }

    @FXML
    void listViewClicked(ActionEvent event) {
        String text = areaSelectShow.getText();
        String text1 = selectShow.getText();
        String[] parts = listView.getSelectionModel().getSelectedItem().toString().split(" / ");
        String name = parts[0];
        service.updateSchema(name, text, text1);
    }

    @FXML
    void compareSequencesFunction(ActionEvent event){
        ObservableList<String> listObs = FXCollections.observableArrayList();
        Vector<String> vec = new Vector<String>();
        Integer parse;
        String initialSequence = selectShow.getText();
        String inputSequence = compareSequences.getText();
        Integer length = inputSequence.length();
        for (parse = 0; parse < length; parse++)
        {
            char c1 = initialSequence.charAt(parse);
            char c2 = inputSequence.charAt(parse);

            if (c1 != c2)
            {
                vec.add("Position " + (parse + 1) + ": " + c1 + " -> " + c2);
            }
        }

        if (vec.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Mutations: ");
            alert.setContentText("There are no mutations!");
            alert.showAndWait().ifPresent(rs -> {
                if (rs == ButtonType.OK) {
                    System.out.println("Pressed OK.");
                }
            });
        }
        else{
            listObs.addAll(vec);
            pointMutationsListView.setItems(listObs);
        }
    }


}