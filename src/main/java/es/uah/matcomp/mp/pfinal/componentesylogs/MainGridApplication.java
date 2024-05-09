package es.uah.matcomp.mp.pfinal.componentesylogs;

import ClasesProyecto.Global;
import ClasesProyecto.Individuos.Avanzado;
import ClasesProyecto.Individuos.Individuo;
import ClasesProyecto.Individuos.básico;
import ClasesProyecto.Mapa.Casilla;
import ClasesProyecto.Mapa.Mapa;
import Excepciones.Muerte;
import Listas.ListasSE.Map;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static javafx.application.Application.launch;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

public class MainGridApplication extends Application {
    /**
     * Generamos un log de sistema para esta clase
     *
     * Necesitamos instalar el paquete apache.logging.log4j.core, en su versión más reciente.
     * Para instalarlo, Open Module Settings -> libraries -> + en la lista -> From maven
     *
     * Además, se necesita configurar un fichero log4j2.xml en la carpeta resources, con la estructura e información
     * del sistema de log que vamos a usar.
     *
     *
     * Ejemplo:

     <?xml version="1.0" encoding="UTF-8"?>
     <Configuration status="DEBUG">
     <Appenders>
     <Console name="LogToConsole" target="SYSTEM_OUT">
     <PatternLayout pattern="%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n"/>
     </Console>
     <RollingFile name="LogToRollingFile" fileName="logs/app.log"
     filePattern="logs/$${date:yyyy-MM}/app-%d{MM-dd-yyyy}-%i.log.gz">
     <PatternLayout>
     <Pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} - %-5level - %logger - %msg%n</Pattern>
     </PatternLayout>
     <Policies>
     <TimeBasedTriggeringPolicy />
     <SizeBasedTriggeringPolicy size="10 MB"/>
     </Policies>
     </RollingFile>

     </Appenders>
     <Loggers>
     <!-- Elimina duplicados con additivity=false -->
     <Logger name="es.uah" level="info" additivity="false">
     <AppenderRef ref="LogToRollingFile"/>
     <AppenderRef ref="LogToConsole"/>
     </Logger>
     <Root level="error">
     <AppenderRef ref="LogToConsole"/>
     </Root>
     </Loggers>
     </Configuration>
     * Este fichero de configuración hace que los logs se guarden en un fichero de la carpeta 'logs' del proyecto
     * que si no existe se creará.
     * los ficheros van guardándose, y se pueden añadir descriptores de hora al nombre para
     * que podamos seguir el rastro de cuándo se creó cada fichero de log.
     * Los logs se pueden enviar a consola o a fichero, como está configurado, pero también
     * se pueden enviar por correo electrónico (SMTP) y a bases de datos en otras configuraciones.
     */

    //private static final Logger log = LogManager.getLogger(MainGridApplication.class);


    /**
     * En este ejemplo, vamos a crear programáticamente la ventan en la que trabajaremos.
     */
    static Label[][] labelMap =new Label[15][15];
    GridPane mainGrid = new GridPane();


    @Override
    public void start(Stage primaryStage)  {

        //log.info("Inicio del método de arranque de la aplicación para mostrar un grid de forma programática");



        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                // Aquí podrías instanciar tu clase de celda, más compleja

                // Ejemplo simplificado con un Label
                Label placeholder = new Label("Celda " + i + "," + j);
                labelMap[i][j]= placeholder;
                placeholder.setMinSize(60, 60); // Tamaño mínimo para visualización
                placeholder.setStyle("-fx-border-color: black; -fx-text-alignment: center;");
                mainGrid.add(placeholder, i, j);

                // OJO!: Tal como está programado, pierdo la referencia a los labels...
                //       Si los quisiese usar después, debería guardarlos de alguna manera en algún sitio
                // Pista: los quieres guardar para poder cambiar lo que aparece en pantalla :)
            }
        }


        //Ejemplo para poner 3 labels en la misma celda

        /*Label antonio = new Label("Antonio");
        Label moratilla = new Label("Moratilla");
        Label ocaña = new Label("Ocaña");

        VBox layout = new VBox(antonio,moratilla,ocaña);
        layout.setStyle("-fx-border-color: black; -fx-text-alignment: center;");
        mainGrid.add(layout,15,15);  // OJO: las coordenadas...: el grid responde automáticamente.
*/
        Scene scene = new Scene(mainGrid, 600, 600);
        primaryStage.setTitle("Grid de 10x10 con Componentes Personalizados");
        primaryStage.setScene(scene);
        primaryStage.show();


        Mapa<Integer> mapa = new Mapa<>(15, 15);
        Map<Individuo, Integer[]> individuos = new Map<>();

        Individuo i1 = new básico(1, 10, 0, 0, 0);
        Individuo i2 = new básico(2, 10, 0, 0, 0);
        Individuo i3 = new básico(3, 10, 0, 0, 0, i1, i2);
        Individuo i4 = new básico(4, 10, 0, 0, 0, i1, i2);
        Individuo i5 = new básico(5, 10, 0, 0, 0, i3, i2);
        Individuo i6 = new Avanzado(6, 10, 0, 0, 0, i4, i5);

        i1.setPos(new Integer[]{1, 4});
        i2.setPos(new Integer[]{1, 3});
        i3.setPos(new Integer[]{1, 2});
        i4.setPos(new Integer[]{0, 4});
        i5.setPos(new Integer[]{2, 4});
        i6.setPos(new Integer[]{7, 5});

        Global juego = new Global(mapa, individuos);

        juego.addIndividuo(i1);
        juego.addIndividuo(i2);
        juego.addIndividuo(i3);
        juego.addIndividuo(i4);
        juego.addIndividuo(i5);
        juego.addIndividuo(i6);

        boolean pause = false;
        String texto;
        /*while (!pause) {
            for (int i = 0; i < 15; i++) {
                for (int j = 0; j < 15; j++) {
                    //Label label =labelMap.getFromId(new Integer[]{i, j});
                    //texto = mapa.casillas[i][j].getInfo();
                    //System.out.println(texto);
                    //label.setText(texto);
                    labelMap[i][j].setText(mapa.casillas[14][14].getInfo());
                }
            }


            juego.pasarTurno();

            try {
                Thread.sleep(500);
                System.out.println("aasd");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }*/

/*
        //scene = new Scene(mainGrid, 600, 600);
        //primaryStage.setScene(scene);
        //primaryStage;
*/

    }


    // Ejemplo de cómo usar los LOGS de sistema que se habían definido antes.
    // Los logs trabajan por niveles:
    // trace, debug, info, warn, error y fatal
    //
    // Según la configuración del fichero de log4j2.xml, se guardarán los mensajes
    // del nivel configurado y superiores, pero no inferiores.

    //     log.trace("Enviando una traza de ejecución");
    //   log.debug("Enviado un debug");
    // log.info("Enviando un info");
    //      log.warn("Enviando un aviso");
    //    log.error("Enviando un error");
    //  log.fatal("Enviando una explosión fatal");

    // Los logs sólo operan si la clase utilizada coincide con el patrón que se pone en el log4j2.xml.
    // En este caso el patrón es "es.uah" que coincide con nuestro paquete, por eso funciona.


    //     log.info("Fin del método de arranque de la aplicación para mostrar un grid de forma programática");


    public static void main(String[] args) {
        Mapa<Integer> mapa = new Mapa<>(15, 20);
        Map<Individuo, Integer[]> individuos = new Map<>();


        Individuo i1 = new básico(1, 10, 0, 0, 0);
        Individuo i2 = new básico(2, 10, 0, 0, 0);
        Individuo i3 = new básico(3, 10, 0, 0, 0, i1, i2);
        Individuo i4 = new básico(4, 10, 0, 0, 0, i1, i2);
        Individuo i5 = new básico(5, 10, 0, 0, 0, i3, i2);
        Individuo i6 = new Avanzado(6, 10, 0, 0, 0, i4, i5);


        i1.setPos(new Integer[]{1, 4});
        i2.setPos(new Integer[]{1, 3});
        i3.setPos(new Integer[]{1, 2});
        i4.setPos(new Integer[]{0, 4});
        i5.setPos(new Integer[]{2, 4});
        i6.setPos(new Integer[]{7, 5});


        Global juego = new Global(mapa, individuos);


        juego.addIndividuo(i1);
        juego.addIndividuo(i2);
        juego.addIndividuo(i3);
        juego.addIndividuo(i4);
        juego.addIndividuo(i5);
        juego.addIndividuo(i6);

//juego.pasarTurno();
    /*for (Casilla[] casillas : mapa.casillas){
        for (Casilla casilla : casillas){
            System.out.println(casilla.getInfo());
        }
    }*/
        launch(args);

        while (true) {
            for (int i = 0; i < 15; i++) {
                for (int j = 0; j < 15; j++) {
                    //Label label =labelMap.getFromId(new Integer[]{i, j});
                    //texto = mapa.casillas[i][j].getInfo();
                    //System.out.println(texto);
                    //label.setText(texto);
                    labelMap[i][j].setText(mapa.casillas[14][14].getInfo());
                }
            }

            juego.pasarTurno();

            try {
                Thread.sleep(500);
                System.out.println("aasd");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }


    }

}
