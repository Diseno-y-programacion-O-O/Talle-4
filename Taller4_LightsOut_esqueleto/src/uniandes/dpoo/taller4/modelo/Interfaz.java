package uniandes.dpoo.taller4.modelo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.Scanner;


class Interfaz extends JFrame implements ActionListener {

    private JButton[][] gameButtons;
    private String selectedOption;
    private int valor = 3;

    /**
     * 
     */
    public Interfaz() {

        // Organización general 
        JFrame jframe = new JFrame();
        jframe.setTitle("Lights Out");
        jframe.getContentPane().setBackground(Color.yellow);


        // East side
        JPanel panelEast = new JPanel();
        panelEast.setLayout(new BoxLayout(panelEast, BoxLayout.Y_AXIS)); 
        
        // Creamos los botones y los agregamos al JPanel
        Dimension buttonSize = new Dimension(150,30);
        JButton Nuevo = new JButton("           Nuevo            ");
        Nuevo.setBackground(Color.PINK);
        Nuevo.setPreferredSize(buttonSize);
        Nuevo.setAlignmentX(Component.CENTER_ALIGNMENT);
        Nuevo.setName("Nuevo");
        Nuevo.addActionListener(this);	
        panelEast.add(Nuevo);

        JButton Reiniciar = new JButton("         Reiniciar         ");
        Reiniciar.setBackground(Color.PINK);
        Reiniciar.setPreferredSize(buttonSize);
        Reiniciar.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelEast.add(Reiniciar);
        
        JButton Top = new JButton("           Top 10           ");
        Top.setBackground(Color.PINK);
        Top.setPreferredSize(buttonSize);
        Top.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelEast.add(Top);

        JButton CambiarJugador = new JButton(" Cambiar Jugador ");
        CambiarJugador.setBackground(Color.PINK);
        CambiarJugador.setPreferredSize(buttonSize);
        CambiarJugador.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelEast.add(CambiarJugador);

        //Organización
        panelEast.add(Box.createVerticalGlue());
        panelEast.add(Nuevo);
        panelEast.add(Box.createRigidArea(new Dimension(0,7))); 
        panelEast.add(Reiniciar);
        panelEast.add(Box.createRigidArea(new Dimension(0, 7)));
        panelEast.add(Top);
        panelEast.add(Box.createRigidArea(new Dimension(0, 7)));
        panelEast.add(CambiarJugador);
        panelEast.add(Box.createRigidArea(new Dimension(0, 7)));
        panelEast.add(Box.createVerticalGlue());
        















        // North side
        JPanel generalNorth = new JPanel();
        JPanel panelNorth = new JPanel();
        panelNorth.setLayout(new BoxLayout(panelNorth, BoxLayout.X_AXIS)); 

        JLabel labelSize = new JLabel("Size:   ");
        panelNorth.add (labelSize);
        generalNorth.add (panelNorth);


        
		JPanel buttonPanel = new JPanel();			
        gameButtons = new JButton[valor][valor];
		buttonPanel.setLayout(new GridLayout(valor,valor));
        for(int i = 0; i < valor; i++){
			for(int j = 0; j < valor; j++){
				int random = (int)(Math.random()*3);	
				JButton button = new JButton();			
				gameButtons[i][j] = button;				
				button.setName(""+i+j);					
				button.setBackground(Color.BLACK);		
				if(random == 2)							
				{
					backgroundColor(button);
				}
				button.addActionListener(this);			
				buttonPanel.add(button);				
			}

		}

        String[] opciones = {"3x3","4x4","5x5"};
        JComboBox<String> comboBox = new JComboBox<>(opciones);
        selectedOption = (String) comboBox.getSelectedItem();
        System.out.println("Tamanio seleccionado: " + selectedOption);
            if (selectedOption.equals("3x3")) {
                valor = 3;}
            else if (selectedOption.equals("4x4")) {
                valor = 4;}
            else if (selectedOption.equals("5x5")) {
                valor = 5;}
    
            comboBox.addActionListener (new ActionListener() {
            
            public void actionPerformed(ActionEvent eje) {
            Object source = eje.getSource();
            if (source instanceof JComboBox) {
                JComboBox<String> comboBox2 = (JComboBox<String>) source;
            Object selectedOption = comboBox2.getSelectedItem();
            int newValor = 0;
            System.out.println("Tamanio seleccionado: " + selectedOption);
            String aiuda = selectedOption.toString();

        if (aiuda.equals("3x3")) {
            newValor = 3;
        } else if (aiuda.equals("4x4")) {
            newValor = 4;
        } else if (aiuda.equals("5x5")) {
            newValor = 5;
        }
        // Crear una nueva matriz de botones con el nuevo tamaño
        JButton[][] newGameButtons = new JButton[newValor][newValor];
        buttonPanel.removeAll();
        buttonPanel.setLayout(new GridLayout(newValor, newValor));
        for (int i = 0; i < newValor; i++) {
            for (int j = 0; j < newValor; j++) {
                int random = (int) (Math.random() * 3);
                JButton button = new JButton();
                newGameButtons[i][j] = button;
                button.setName("" + i + j);
                button.setBackground(Color.BLACK);
                if (random == 2) {
                    backgroundColor(button);
                }
                button.addActionListener(this);
                buttonPanel.add(button);
            }
        }
        
        valor = newValor;
        buttonPanel.revalidate();
        buttonPanel.repaint();
        }
        else{
        boolean var = source instanceof JComboBox;
        if (var == false){
        JButton button = (JButton)eje.getSource();		
                String location = button.getName();				
                if(location.equals("Nuevo"))					
                {												
                    randomSetting();
                    return;
                }

		char colChar = location.charAt(0);		
		char rowChar = location.charAt(1);		
		int col = Character.getNumericValue(colChar);	
		int row = Character.getNumericValue(rowChar);	
		
		JButton Selected = new JButton();			
		JButton Top = new JButton();				
		JButton Left = new JButton();				
		JButton Right = new JButton();				
		JButton Bottom = new JButton();
		
        Selected = gameButtons[col][row];			
		backgroundColor(Selected);					
		
		try{
			Top = gameButtons[col-1][row];			
			backgroundColor(Top);}					
		catch(ArrayIndexOutOfBoundsException i){
        }
		try{
			Left = gameButtons[col][row-1];			
			backgroundColor(Left);}				
		catch(ArrayIndexOutOfBoundsException i){
		}
		try{
			Right = gameButtons[col][row+1];		
			backgroundColor(Right);}				
		catch(ArrayIndexOutOfBoundsException i){
		}
		try{
			Bottom = gameButtons[col+1][row];		
			backgroundColor(Bottom);}			
		catch(ArrayIndexOutOfBoundsException i){			
		}									
	  } 
    }
    }
    }
     

    );

        panelNorth.add (comboBox);
        generalNorth.add(panelNorth);







        JLabel labelDifficulty = new JLabel("  Difficulty: ");
        panelNorth.add (labelDifficulty);
        generalNorth.add (panelNorth);
      
        JRadioButton facil=new JRadioButton("Easy");    
        JRadioButton medio=new JRadioButton("Medium");    
        JRadioButton dificil=new JRadioButton("Difficult");      
        ButtonGroup bg = new ButtonGroup();    
        bg.add(facil); bg.add(medio); bg.add(dificil); 
        panelNorth.add (facil);
        panelNorth.add (medio);
        panelNorth.add (dificil);
        generalNorth.add (panelNorth);
            
        // Center side	
        

        // South side
        JButton btnSouth = new JButton("SOUTH");
        btnSouth.setBackground(Color.RED); 
        btnSouth.setOpaque(true);

        JPanel generalSouth = new JPanel();
        JPanel panelSouth = new JPanel();
        panelSouth.setLayout(new FlowLayout(FlowLayout.CENTER, 4, 8)); 

        JLabel labelJugadas = new JLabel("Moves:");
        panelSouth.add (labelJugadas);
        generalSouth.add (panelSouth);

        JLabel labelPuntaje = new JLabel("0            ");
        panelSouth.add (labelPuntaje);
        generalSouth.add (panelSouth);

        JLabel labelJugador = new JLabel("Jugador:");
        panelSouth.add (labelJugador);
        generalSouth.add (panelSouth);

        JTextArea nombre = new JTextArea("Nombre"); 
        panelSouth.add (nombre );
        generalSouth.add (panelSouth);


        
        


        // Configurar distancia entre partes
        jframe.setLayout(new BorderLayout(5, 5));  


        // Añadir north layout
        jframe.add(generalNorth, BorderLayout.NORTH);
       
        
        // Añadir south layout
        jframe.add(generalSouth, BorderLayout.SOUTH); 
    

        // Añadir east layout
        jframe.add(panelEast, BorderLayout.EAST);  
    

        // Añadir center layout
        jframe.add(buttonPanel, BorderLayout.CENTER);  
        

        // Tamaño y visibilidad ventana
        jframe.setSize(600,600);    
        jframe.setVisible(true); 
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        boolean var = source instanceof JComboBox;
        if (var == false){
        JButton button = (JButton)e.getSource();		
                String location = button.getName();				
                if(location.equals("Nuevo"))					
                {												
                    randomSetting();
                    return;
                }

		char colChar = location.charAt(0);		
		char rowChar = location.charAt(1);		
		int col = Character.getNumericValue(colChar);	
		int row = Character.getNumericValue(rowChar);	
		
		JButton Selected = new JButton();			
		JButton Top = new JButton();				
		JButton Left = new JButton();				
		JButton Right = new JButton();				
		JButton Bottom = new JButton();
		
        Selected = gameButtons[col][row];			
		backgroundColor(Selected);					
		
		try{
			Top = gameButtons[col-1][row];			
			backgroundColor(Top);}					
		catch(ArrayIndexOutOfBoundsException i){
        }
		try{
			Left = gameButtons[col][row-1];			
			backgroundColor(Left);}				
		catch(ArrayIndexOutOfBoundsException i){
		}
		try{
			Right = gameButtons[col][row+1];		
			backgroundColor(Right);}				
		catch(ArrayIndexOutOfBoundsException i){
		}
		try{
			Bottom = gameButtons[col+1][row];		
			backgroundColor(Bottom);}			
		catch(ArrayIndexOutOfBoundsException i){			
		}									
	  } 
    }
	
	private void backgroundColor(JButton ColorBoton)
	{
		if(ColorBoton.getBackground()==Color.BLACK){											
			ColorBoton.setBackground(Color.YELLOW);}
		else{
			ColorBoton.setBackground(Color.BLACK);}
	}
	
	private void randomSetting(){
		for(JButton ColorBoton[]: gameButtons){
			for(JButton c : ColorBoton){
				int random = (int)(Math.random()*6);	
				if(random == 3){
					backgroundColor(c);}	
			}
		  }
	    }
      }
    
    

