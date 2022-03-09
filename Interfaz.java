/**
 * @author Angel Luis Hans Siria
 * @version 1.0
 */

package calculadoragui;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


//Prueba commit
public class Interfaz implements ActionListener{

    JTextField pantallaSup, pantallaInf;                                                                                                                                         //campo de texto 1 y 2 
    Panel panPantalla, panBotMemo, panBotOpe;                                                                                                                                     // pantalla, botones memoria y botones operaciones
    JPanel panTeclado, panNumeros;                                                                                                                                                 /// teclado y botonesnumericos
    JButton mc, mr, ms, mMas, mMenos, numeros[], operaciones[];                                                                 // Lo dejaria igual
    String operadores[]={"R", "C", "+", "/", "-" ,"*", "="},  ax="";                                                                                   // operaciones
    float digito1, digito2, resultado, memoria;                                                                                                                                            //variables para las operaciones  //n1 y digito2 son los numero de las operaciones, resultado numero resultado, memoria memoria
    int tipOp;                                                                                                                                                       //para controlar el tipo de operacion que se realiza
    boolean t=false;//control sobre escribir un nuevo numero despues de alguna operacion cambia a true cuando se ha realizado una operacion

    
    public Interfaz(){

        JFrame jfMain = new JFrame("Calculator");
        jfMain.setLayout(new BorderLayout(4, 4));

        norte();
        sur();

        jfMain.add(panPantalla, BorderLayout.NORTH);
        jfMain.add(panTeclado, BorderLayout.CENTER);

        jfMain.setLocation(100, 80);
        jfMain.setResizable(false);
        jfMain.setVisible(true);
        jfMain.setSize(300, 380);
        jfMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
/**
 * En este metodo norte se crea el panel de las pantallas, dentro de este panel encontramos dos JTextField
 */
    public void norte(){

        panPantalla = new Panel(null);        

        pantallaSup = new JTextField("");
        pantallaInf = new JTextField("0");

        pantallaSup.setHorizontalAlignment(JTextField.RIGHT); 
        pantallaInf.setHorizontalAlignment(JTextField.RIGHT); 

        //Quitar bordes a los campos de texto
        pantallaSup.setBorder(BorderFactory.createLineBorder(Color.white));
        pantallaInf.setBorder(BorderFactory.createLineBorder(Color.white));

        //desabilitando los campos de texto
        pantallaSup.setEditable(false);
        pantallaInf.setEditable(false);

        pantallaSup.setBackground(Color.white);
        pantallaInf.setBackground(Color.white);

        panPantalla.add(pantallaSup); panPantalla.add(pantallaInf);

        pantallaSup.setBounds(35, 10, 200, 15);
        pantallaInf.setBounds(35, 25, 200, 30);

        panPantalla.setSize(270, 47);
        panPantalla.setVisible(true);

    }
/**
 * En este metodo sur se crea el panel del teclado donde dentro contendra:
 * <br>- Panel de botones de memoria
 *  <br>- Panel de numeros
 *  <br>- Panel de Operaciones
 */
    public void sur(){

        panTeclado = new JPanel(new BorderLayout(6, 50));
        panTeclado.setLayout(new BorderLayout(4, 4));

        botMem();
        botNum();
        botOpe();

        panTeclado.add(panBotMemo, BorderLayout.NORTH);  
        panTeclado.add(panNumeros, BorderLayout.CENTER); 
        panTeclado.add(panBotOpe, BorderLayout.EAST); 

        panTeclado.setSize(270, 330);
    }

    
    /**
     * En este método encontramos los botones de Memoria, se le asignara el tipo de letra, el tamaño y la posición de los mismos
     */
    public void botMem(){

        panBotMemo = new Panel(null);

        mc = new JButton("MC");  mr = new JButton("MR");
        ms = new JButton("MS"); mMas = new JButton("M+");
        mMenos = new JButton("M-");

        mc.setFont(new Font("Arial", Font.BOLD, 11));
        mr.setFont(new Font("Arial", Font.BOLD, 11));
        ms.setFont(new Font("Arial", Font.BOLD, 11));
        mMas.setFont(new Font("Arial", Font.BOLD, 11));
        mMenos.setFont(new Font("Arial", Font.BOLD, 11));

        mc.setMargin(new Insets(1, 1, 1, 1)); mr.setMargin(new Insets(1, 1, 1, 1));
        ms.setMargin(new Insets(1, 1, 1, 1)); mMas.setMargin(new Insets(1, 1, 1, 1));
        mMenos.setMargin(new Insets(1, 1, 1, 1)); 

        mc.setBounds(35, 0, 33, 33); mr.setBounds(78, 0, 33, 33); ms.setBounds(121, 0, 33, 33);
        mMas.setBounds(164, 0, 33, 33); mMenos.setBounds(207, 0, 33, 33);

        panBotMemo.add(mc); panBotMemo.add(mr); panBotMemo.add(ms); panBotMemo.add(mMas); panBotMemo.add(mMenos);

        mc.addActionListener(this); mr.addActionListener(this); ms.addActionListener(this);
        mMas.addActionListener(this); mMenos.addActionListener(this);

        panBotMemo.setSize(270, 45);
        panBotMemo.setVisible(true);        
    }

 /**
  * El método botNum es el encargado de generar los botones numéricos del 0 al 9, los posiciona dentro del panNumeros
  */
    
    public void botNum(){

        panNumeros = new JPanel(null);

        int nx3=121, nx2=121, nx1=121, n3y=0, n2y=43, n1y=86;
        numeros = new JButton[11];

        //*****************************************
        //bloque para crear los botones, añadirlos y asignar numeros
        for (int i=0; i<=10; i++){

            if(i<=9){
                numeros[i] = new JButton(""+i);
                panNumeros.add(numeros[i]);
                numeros[i].setMargin(new Insets(1, 1, 1, 1));
                numeros[i].addActionListener(this);  
            }
            else{
                numeros[i] = new JButton(".");
                panNumeros.add(numeros[i]);
                numeros[i].setMargin(new Insets(1, 1, 1, 1));
                numeros[i].addActionListener(this);
            }
        }

        //******************************************
        //bloque para posicionar botones
        for(int i=10; i>=0; i--){

            if(i==10){
                numeros[i].setBounds(121, 129, 35, 35);
            }
            else{
                if(i<=9 && i>=7){
                    numeros[i].setBounds(nx3, n3y, 35, 35);
                    nx3-=43;
                }
                else if(i<=6 && i>=4){   
                    n3y+=43;                    
                    numeros[i].setBounds(nx2, n2y, 35, 35);
                    nx2-=43;
                }
                else if(i<=3 && i>=1){
                    n3y+=43;                    
                    numeros[i].setBounds(nx1, n1y, 35, 35);
                    nx1-=43;
                }
                else if(i==0){
                    numeros[i].setBounds(35, 129, 78, 35);                    
                }
            }                
        }

        panNumeros.setSize(170, 150);
        panNumeros.setVisible(true);
    }
/**
 * El método botOpe es el encargado de crear un nuevo panel e insertar en el los botones de las diferentes operaciones que se encuentra en el array de operadores
 * los posiciona y los mete dentro de panBotOpe
 */
    public void botOpe(){

        panBotOpe = new Panel(null);

        int c=0, x=0, y=0;

        operaciones = new JButton[7];

        for(int i=0; i<=6; i++){
            if(c<=1){

                operaciones[i] = new JButton(operadores[i]);
                panBotOpe.add(operaciones[i]);

                    operaciones[i].setBounds(x, y, 30, 35);

                    operaciones[i].setMargin(new Insets(1, 1, 1, 1));
                    operaciones[i].addActionListener(this);
                    x+=33;
                    c++;               
            }
            else{
                if(i==6){
                    x=0; y+=43;
                    operaciones[i] = new JButton(operadores[i]);
                    panBotOpe.add(operaciones[i]);

                    operaciones[i].setBounds(x, y, 65, 35);

                    operaciones[i].setMargin(new Insets(1, 1, 1, 1));
                    operaciones[i].addActionListener(this);
                    x+=33;
                    c++;
                }
                else{
                    c=0;
                    x=0; y+=43;
                    operaciones[i] = new JButton(operadores[i]);
                    panBotOpe.add(operaciones[i]);

                    operaciones[i].setBounds(x, y, 30, 35);

                    operaciones[i].setMargin(new Insets(1, 1, 1, 1));
                    operaciones[i].addActionListener(this);
                    x+=33;
                    c++;   
                }                             
            }                

        }

        panBotOpe.setVisible(true);
        
        panBotOpe.setSize(120, 200);
    }

 /**
  * Este metodo es el encargado de comprobar que un valor de String es entero. 
  * @param ax Recibe un String
  * @return devuelve true si es un Int o false si no lo es
  */
    
    public boolean isN(String ax){

        try{
            int n = Integer.parseInt(ax);
            return true;
        }catch(NumberFormatException e){
            return false;
        }

    }

    @Override
    
 /**
  * Esta función es la que lleva la operativa de la calculadora y sobre la que hemos actuado refactorizando y sacando las operaciones en métodos independientes para 
  * su reutilización.
  */
    public void actionPerformed(ActionEvent e) {

        String  op="";

        if(isN(e.getActionCommand())){ //cuando se oprimen numeros

            if(pantallaSup.getText().equals("")){
                ax += e.getActionCommand();
                pantallaInf.setText(ax);
            }
            else{
                if(tipOp==0){
                    if(t){
                        ax=""; 

                        pantallaSup.setText(pantallaInf.getText());                        
                        ax += e.getActionCommand();
                        pantallaInf.setText(ax);    
                        t = false;
                    }
                    else{
                        ax="";
                        ax += pantallaInf.getText()+e.getActionCommand();
                        pantallaInf.setText(ax);
                    }                
                }else{
                    ax="";
                    ax += pantallaInf.getText()+e.getActionCommand();
                    pantallaInf.setText(ax);
                }
            }            
        }
        else{//cuando se oprime el resto de botones

            if(e.getActionCommand().equals("R") ){
                pantallaSup.setText("");
                Float a = Float.parseFloat(pantallaInf.getText());
                pantallaInf.setText(""+Math.sqrt(a)); 
            }
            if(e.getActionCommand().equals("C") ){ //para reiniciar valores y limpiar pantalla
                tipOp=0; digito1 = 0; digito2 =0; resultado=0; pantallaSup.setText(""); pantallaInf.setText("0"); ax="";
            }   
            if(e.getActionCommand().equals("MC")){//para limpiar la memoria de la calculadora
                ms.setForeground(Color.black);
                pantallaSup.setText(""); pantallaInf.setText("0");
                memoria=0;
            }
            if(e.getActionCommand().equals("MR")){//para mostrar valor almacenado en la memoria
                pantallaSup.setText("");
                pantallaInf.setText(String.valueOf(memoria));
            }if(e.getActionCommand().equals("MS")){//guardar un valor en la memoria
                ms.setForeground(Color.red);
                memoria = Float.parseFloat(pantallaInf.getText());
            }
            if(e.getActionCommand().equals("M+")){//sumar valor de la pantalla con el valor de la memoria
                memoria += Float.parseFloat(pantallaInf.getText());
            }
            if(e.getActionCommand().equals("M-")){//restar valor de la pantalla con el valor de la memoria
                memoria -= Float.parseFloat(pantallaInf.getText());
            }    
            if(e.getActionCommand().equals(".")){//usar el punto para los decimales
                ax="";
                if(numeros[10].isEnabled()){
                    numeros[10].setEnabled(false);
                    ax = pantallaInf.getText() +".";
                    pantallaInf.setText(ax);
                }
            }
            
            operacionSuma(e);
            
            operacionResta(e);
            
            operacionMultiplicar(e);
            operacionDividir(e);
            operacionResultado(e);
        }        
    }       
/**
 * Este metodo es el encargado de generar el resultado y mostrarlo en pantalla
 * @param e Recibe el ActionEventent de la función actionPerformed
 * @throws NumberFormatException Lanza la excepción NumberFormatException
 */
    public void operacionResultado(ActionEvent e) throws NumberFormatException {
        if(e.getActionCommand().equals("=") && !pantallaInf.getText().equals("")){
            t = true;
            if(tipOp==1){//operacion para la suma
                tipOp = 0;
                ax="";
                ax+=pantallaSup.getText() + pantallaInf.getText();
                pantallaSup.setText(ax);
                digito2 = Float.parseFloat(pantallaInf.getText());
                resultado=digito1+digito2;
                pantallaInf.setText(String.valueOf(resultado));
            }
            else if(tipOp==2){ //operacion para la resta
                tipOp = 0;
                ax="";
                ax+=pantallaSup.getText()+pantallaInf.getText();
                pantallaSup.setText(ax);
                digito2 = Float.parseFloat(pantallaInf.getText());
                resultado=digito1-digito2;
                pantallaInf.setText(String.valueOf(resultado));
            }
            if(tipOp==3){ //operacion para la multiplicacion
                tipOp = 0;
                ax="";
                ax+=pantallaSup.getText()+pantallaInf.getText();
                pantallaSup.setText(ax);
                digito2 = Float.parseFloat(pantallaInf.getText());
                resultado=digito1*digito2;
                pantallaInf.setText(String.valueOf(resultado));
            }
            if(tipOp==4){ //operacion para la division
                if(Float.parseFloat(pantallaInf.getText())!=0){
                    tipOp = 0;
                    ax="";
                    ax+=pantallaSup.getText()+pantallaInf.getText();
                    pantallaSup.setText(ax);
                    digito2 = Float.parseFloat(pantallaInf.getText());
                    resultado=digito1/digito2;
                    pantallaInf.setText(String.valueOf(resultado));
                }
                
            }
        }
    }
/**
 * Este metodo es el encargado de realizar la operación suma
 * @param e Recibe el ActionEventent de la función actionPerformed
 * @throws NumberFormatException Lanza la excepción NumberFormatException
 */
    public void operacionSuma(ActionEvent e) throws NumberFormatException {
        if(e.getActionCommand().equals("+") ){//boton suma
            numeros[10].setEnabled(true);
            ax="";
            if(tipOp==1){
                
            }else if(tipOp==0 ){//validacion para no chocar con otras operaciones
                if(pantallaSup.getText().equals("") ){
                    digito1 = Float.parseFloat(pantallaInf.getText());
                    ax += pantallaSup.getText()+pantallaInf.getText();
                    pantallaSup.setText(ax+" + ");
                    pantallaInf.setText("");
                    tipOp = 1;
                }
                
                else {
                    if(!t){//validacion para nueva operacion
                        digito1 = Float.parseFloat(pantallaInf.getText());
                        ax += pantallaInf.getText();
                        pantallaSup.setText(ax+" + ");
                        pantallaInf.setText("");
                        tipOp = 1;
                    }
                    else{//usar otras operaciones con la suma
                        digito1 = Float.parseFloat(pantallaInf.getText());
                        ax += pantallaSup.getText();
                        pantallaSup.setText(ax+" + ");
                        pantallaInf.setText("");
                        tipOp = 1;
                    }
                }
            }
        }
    }

    /**
     * En esta función se llevará a cabo la operación de multiplicación
     * @param e Recibe el ActionEventent de la función actionPerformed
     * @throws NumberFormatException Lanza la excepción NumberFormatException
     */
    public void operacionMultiplicar(ActionEvent e) throws NumberFormatException {
        if(e.getActionCommand().equals("*") ){//cuando se decide multiplicar
            numeros[10].setEnabled(true);
            ax="";
            if(tipOp==3){
                
            }else if(tipOp==0){//validacion para no chocar con otras operaciones
                if(pantallaSup.getText().equals("")){
                    digito1 = Float.parseFloat(pantallaInf.getText());
                    ax += pantallaSup.getText()+pantallaInf.getText();
                    pantallaSup.setText(ax+" * ");
                    pantallaInf.setText("");
                    tipOp = 3;
                }
                else{
                    if(!t){//validacion para nueva operacion
                        digito1 = Float.parseFloat(pantallaInf.getText());
                        ax += pantallaInf.getText();
                        pantallaSup.setText(ax+" * ");
                        pantallaInf.setText("");
                        tipOp = 3;
                    }
                    else{//usar otras operaciones con la suma
                        digito1 = Float.parseFloat(pantallaInf.getText());
                        ax += pantallaSup.getText();
                        pantallaSup.setText(ax+" * ");
                        pantallaInf.setText("");
                        tipOp = 3;
                    }
                }
            }
        }
    }

    /**
     * En esta función se llevará a cabo la operación de división
     * @param e Recibe el ActionEventent de la función actionPerformed
     * @throws NumberFormatException Lanza la excepción NumberFormatException
     */
    public void operacionDividir(ActionEvent e) throws NumberFormatException {
        if(e.getActionCommand().equals("/") ){//cuando se decide dividir
            numeros[10].setEnabled(true);
            ax="";
            if(tipOp==4){
                
            }else if(tipOp==0){//validacion para no chocar con otras operaciones
                if(pantallaSup.getText().equals("")){
                    digito1 = Float.parseFloat(pantallaInf.getText());
                    ax += pantallaSup.getText()+pantallaInf.getText();
                    pantallaSup.setText(ax+" / ");
                    pantallaInf.setText("");
                    tipOp = 4;
                }
                else{
                    if(!t){//validacion para nueva operacion
                        digito1 = Float.parseFloat(pantallaInf.getText());
                        ax += pantallaInf.getText();
                        pantallaSup.setText(ax+" / ");
                        pantallaInf.setText("");
                        tipOp = 4;
                    }
                    else{//usar otras operaciones con la suma
                        digito1 = Float.parseFloat(pantallaInf.getText());
                        ax += pantallaSup.getText();
                        pantallaSup.setText(ax+" / ");
                        pantallaInf.setText("");
                        tipOp = 4;
                    }
                }
            }
        }
    }
    /**
     * En esta función se llevará a cabo la operación de resta
     * @param e Recibe el ActionEventent de la función actionPerformed
     * @throws NumberFormatException Lanza la excepción NumberFormatException
     */
    public void operacionResta(ActionEvent e) throws NumberFormatException {
        if(e.getActionCommand().equals("-") ){//cuando se decide restar
            numeros[10].setEnabled(true);
            ax="";
            if(tipOp==2){
                
            }else if(tipOp==0){//validacion para no chocar con otras operaciones
                if(pantallaSup.getText().equals("")){
                    digito1 = Float.parseFloat(pantallaInf.getText());
                    ax += pantallaSup.getText()+ pantallaInf.getText();
                    pantallaSup.setText(ax+" - ");
                    pantallaInf.setText("");
                    tipOp = 2;
                }
                else{
                    if(!t){//validacion para nueva operacion
                        digito1 = Float.parseFloat(pantallaInf.getText());
                        ax += pantallaInf.getText();
                        pantallaSup.setText(ax+" - ");
                        pantallaInf.setText("");
                        tipOp = 2;
                    }
                    else{//usar otras operaciones con la suma
                        digito1 = Float.parseFloat(pantallaInf.getText());
                        ax += pantallaSup.getText();
                        pantallaSup.setText(ax+" - ");
                        pantallaInf.setText("");
                        tipOp = 2;
                    }
                }
            }
        }
    }
}