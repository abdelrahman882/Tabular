
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

public class Tabular_Method {
    int min = 100000;
    LinkedList<LinkedList<String>> prime = new LinkedList<LinkedList<String>>(); //////////////// looooooooooooooooooook
    //////////////// heeeeeeeeeeeeerrreeeee
    LinkedList<String> essential = new LinkedList<String>();//////////////////////////////////// and
    //////////////////////////////////// herrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrre
   private JRadioButton printfile ;
    private JFrame frame;
    private JTextField input;
    private JLabel finalresult;
    private String strinput;
    private String strinputd;
    private JRadioButton steps;
    private boolean sbool;
    private boolean fbool;
    private int varsnum;
    private JLabel resultlabel;
    private JTextField input2;
    private JLabel label;
    private JLabel lblM;
    private JLabel label_1;
    private JLabel lblD;
    private JTextArea stepso;
    private JTextField textfilename;
    private JLabel lblOrEnterA;
    private JLabel lblWriteMintermsAt;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    Tabular_Method window = new Tabular_Method();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public Tabular_Method() {
        strinput = new String();
        strinputd = new String();
        sbool = false;
        fbool=false;
        varsnum = 0;
        initialize();

    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 1103, 724);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblOnForm = new JLabel("on form 0,1,2,3,...,5");
        lblOnForm.setBounds(236, 89, 207, 16);
        frame.getContentPane().add(lblOnForm);

        JLabel EMD = new JLabel("enter minterms");
        EMD.setForeground(Color.DARK_GRAY);
        EMD.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.BOLD, 40));
        EMD.setBounds(194, 23, 291, 92);
        frame.getContentPane().add(EMD);

        input = new JTextField();
        input.setBackground(Color.WHITE);
        input.setBounds(68, 118, 522, 44);
        frame.getContentPane().add(input);
        input.setColumns(10);

        JButton submit = new JButton("submit");
        submit.addActionListener(new ActionListener() {
            @Override
            /**
             * Start calculations when clicking on Submit
             */
            public void actionPerformed(ActionEvent arg0) {
            	if(input.getText().length()!=0){ // get input from gui
                strinput = input.getText();
                strinputd = input2.getText();
            }else if (textfilename.getText().length()!=0){ // get input from text file
        		try {
        			File file = new File(textfilename.getText()+".txt");
        			Scanner sc = new Scanner(file);

        			strinput = sc.nextLine();
        			strinputd = sc.nextLine();

        	        sc.close();
        			
        		} catch (IOException e) {
        			submit.setBackground(new Color(255, 0, 0));
					varsnum = 0;
					prime.clear();
					essential.clear();
        		}
            }
            	if(strinput.length()==1&&strinput.charAt(0)=='0'){
            		finalresult.setText("A'");
            	}else{
            	
            	sbool = steps.isSelected();
            	fbool = printfile.isSelected();
            	try {
                    operate();
                    resultlabel.setEnabled(true);
                    submit.setBackground(new Color(230, 230, 230));

                    } catch (Exception e) {
    					submit.setBackground(new Color(255, 0, 0));
    					varsnum = 0;
    					prime.clear();
    					essential.clear();
    				}}
            }

        });
        submit.setForeground(Color.DARK_GRAY);
        submit.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.BOLD, 20));
        submit.setBackground(Color.LIGHT_GRAY);
        submit.setBounds(253, 272, 123, 44);
        frame.getContentPane().add(submit);

        steps = new JRadioButton("show steps");
        steps.setBounds(68, 288, 127, 25);
        frame.getContentPane().add(steps);

        input2 = new JTextField();
        input2.setColumns(10);
        input2.setBackground(Color.WHITE);
        input2.setBounds(700, 118, 359, 44);
        frame.getContentPane().add(input2);

        JLabel EDC = new JLabel("enter don't cares");
        EDC.setForeground(Color.DARK_GRAY);
        EDC.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.BOLD, 40));
        EDC.setBounds(719, 23, 291, 92);
        frame.getContentPane().add(EDC);

        label = new JLabel("\u03A3");
        label.setFont(new Font("Tahoma", Font.PLAIN, 60));
        label.setBounds(12, 98, 68, 64);
        frame.getContentPane().add(label);

        lblM = new JLabel("m");
        lblM.setBounds(46, 141, 56, 16);
        frame.getContentPane().add(lblM);

        label_1 = new JLabel("\u03A3");
        label_1.setFont(new Font("Tahoma", Font.PLAIN, 60));
        label_1.setBounds(639, 98, 68, 64);
        frame.getContentPane().add(label_1);

        lblD = new JLabel("d");
        lblD.setBounds(677, 141, 56, 16);
        frame.getContentPane().add(lblD);

        resultlabel = new JLabel("RESULT:");
        resultlabel.setEnabled(false);
        resultlabel.setBounds(440, 288, 56, 16);
        frame.getContentPane().add(resultlabel);

        finalresult = new JLabel("");
        finalresult.setFont(new Font("Tahoma", Font.BOLD, 25));
        finalresult.setBounds(508, 226, 515, 100);
        frame.getContentPane().add(finalresult);

        JPanel resultspace = new JPanel();
        resultspace.setBackground(Color.WHITE);
        resultspace.setBorder(new EtchedBorder(EtchedBorder.RAISED, Color.BLACK, Color.BLACK));
        resultspace.setBounds(68, 329, 928, 319);
        frame.getContentPane().add(resultspace);
        GridBagLayout gbl_resultspace = new GridBagLayout();
        gbl_resultspace.columnWidths = new int[] { 462, 0 };
        gbl_resultspace.rowHeights = new int[] { 315, 0 };
        gbl_resultspace.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
        gbl_resultspace.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
        resultspace.setLayout(gbl_resultspace);

        JScrollPane scrollPane = new JScrollPane();
        GridBagConstraints gbc_scrollPane = new GridBagConstraints();
        gbc_scrollPane.fill = GridBagConstraints.BOTH;
        gbc_scrollPane.gridx = 0;
        gbc_scrollPane.gridy = 0;
        resultspace.add(scrollPane, gbc_scrollPane);

        stepso = new JTextArea();
        scrollPane.setViewportView(stepso);
        stepso.setFont(new Font("Dialog", Font.PLAIN, 22));
        
        textfilename = new JTextField();
        textfilename.setBounds(485, 175, 396, 38);
        frame.getContentPane().add(textfilename);
        textfilename.setColumns(10);
        
        lblOrEnterA = new JLabel("or enter a text file name");
        lblOrEnterA.setFont(new Font("Tahoma", Font.BOLD, 25));
        lblOrEnterA.setBounds(146, 182, 313, 31);
        frame.getContentPane().add(lblOrEnterA);
        
        lblWriteMintermsAt = new JLabel("write minterms at first line and don't cares at the second line on the same form . result will be printed at result.txt");
        lblWriteMintermsAt.setBounds(185, 220, 718, 25);
        frame.getContentPane().add(lblWriteMintermsAt);
        
         printfile = new JRadioButton("print steps to result.text");
        printfile.setBounds(68, 261, 165, 25);
        frame.getContentPane().add(printfile);

    }
/**
 * get the max value of str2 (the input)
 * @param str2 is the input do'tcares or minterms
 * @param ww  is the number of integers in str2
 * @return max integer in the string 
 */
    private int getmaxint(StringBuilder str2, int ww) {
        StringBuilder str = new StringBuilder();
        str.append(str2.toString());
        if(str.length()==0){

            return 0 ;
        }
        int max = 0;
        for (int i = 0; i < ww; i++) {
            float O = 0;
            for (int l = 0; true; l--) {

                if (str.length() == 1) {
                    O += Character.getNumericValue(str.charAt(0)) * Math.pow(10, l);
                    O = (float) (O * Math.pow(10, (l * -1)));
                    break;
                } else if (str.charAt(0) == ',') {
                    str.deleteCharAt(0);
                    O = (float) (O * Math.pow(10, ((l + 1) * -1)));
                    break;
                }
                O += Character.getNumericValue(str.charAt(0)) * Math.pow(10, l);
                str.deleteCharAt(0);

            }
            if (O > max) {
                max = (int) O;
            }
        }
        return max;

    }

    void modresult(LinkedList<elem> ll, String array1[][]) {

        for (int i = 0; i < ll.size(); i++) {
            array1[i][0] = ll.get(i).str;
            array1[i][1] = ll.get(i).nums;

        }
    }

    /**
     * fill the first parameter for index x to index y with numbrs in str separated by ','
     */
    
    private void toarray(char[][] minterms, StringBuilder str, int x, int y) {
        for (int i = x; i < y; i++) {
            String temp = new String();
            float O = 0;
            for (int l = 0; true; l--) {

                if (str.length() == 1) {
                    O += Character.getNumericValue(str.charAt(0)) * Math.pow(10, l);
                    O = (float) (O * Math.pow(10, (l * -1)));
                    break;
                } else if (str.charAt(0) == ',') {
                    str.deleteCharAt(0);
                    O = (float) (O * Math.pow(10, ((l + 1) * -1)));
                    break;
                }
                O += Character.getNumericValue(str.charAt(0)) * Math.pow(10, l);
                str.deleteCharAt(0);
            }

            temp = Integer.toBinaryString((int) O);
            if (temp.length() != varsnum) {
                StringBuilder ss = new StringBuilder();
                for (int r = 0; r < temp.length(); r++) {
                    ss.append(temp.charAt(r));
                }
                while (ss.length() < varsnum) {
                    ss.insert(0, '0');
                }
                temp = ss.toString();
            }

            for (int j = 0; j < varsnum; j++) {
                minterms[i][j] = temp.charAt(j);
            }
        }
    }

    int getnumberofones(char ar[][], int x) {
        int answer = 0;
        for (int i = 0; i < ar[x].length; i++) {
            if (ar[x][i] == '1') {
                answer++;

            }

        }
        return answer;

    }

    String makestring(char ar[][], int x) {
        String answer = "";
        for (int i = 0; i < ar[x].length; i++) {
            answer += ar[x][i];

        }

        return answer;
    }

    String makestring1d(char ar[]) {
        String answer = "";
        for (int i = 0; i < ar.length; i++) {
            answer += ar[i];

        }

        return answer;
    }

    boolean compare(String one, String two) {
        char ar1[] = one.toCharArray();
        char ar2[] = two.toCharArray();

        for (int i = 0; i < one.length(); i++) {
            if (ar1[i] != ar2[i]) {
                return false;
            }

        }
        return true;

    }

   
    class elem {
        String str;
        String nums;
        boolean found;
        /**
         * constructor
         */
        elem(String x, String y) {
            str = new String();
            nums = new String();
            str = x;
            nums = y;
            found = false;

        }

    }

    class elem2 {
        String str;
        LinkedList<Integer> nums = new LinkedList<Integer>();
    }

    
    LinkedList<elem2> makeresult2(LinkedList<elem> result) {
        LinkedList<elem2> z = new LinkedList<elem2>();
        for (int i = 0; i < result.size(); i++) {
            String h = result.get(i).str;
            LinkedList<Integer> carry = new LinkedList<Integer>();
            String q = result.get(i).nums;
            char w[] = q.toCharArray();
            for (int j = 0; j < q.length(); j++) {
                String b = "";
                while (j < q.length() && w[j] != ',') {
                    b += w[j];

                    j++;
                }
                if (b != "") {
                    carry.add(Integer.parseInt(b));
                }

            }
            elem2 pp = new elem2();
            pp.str = h;
            pp.nums = carry;
            z.add(pp);

        }
        return z;

    }
   
    /**
     * on clicking submit ,inputs are set to the global variables and this function is applied
     */
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    private void operate() {
    	PrintWriter writer;
		try {
			writer = new PrintWriter("result.txt", "UTF-8"); //open result file
		} catch (Exception e3) {
			throw null;
		}
    	
        StringBuilder stepsstr = new StringBuilder(); // put all the steps in this StringBuilder while operating 

        // first section converting string input to chars
        
        char arr[] = new char[strinput.length()];
        char arr2[] = new char[strinputd.length()];
        String[] s1=null;
        if(strinput.length()>1){
        s1=strinput.split(",");
        }
        String[] s2=null;
        if(strinputd.length()>1){
        s2=strinputd.split(",");
        }
        if(s1!=null){
for(int i=0 ; i<s1.length;i++){
	if( s1[i].equals("")){
		throw null;
	}
}
        }
        if(s2!=null){
for(int i=0 ; i<s2.length;i++){
	if( s2[i].equals("")){
		throw null;
	}
}    
        }
        arr = strinput.toCharArray();
        arr2 = strinputd.toCharArray();

        StringBuilder str = new StringBuilder();
        StringBuilder str2 = new StringBuilder();
        int mtnum = 0;   //minterms number
        int dcnum = 0;   //don'tcares number
        for (int i = 0; i < arr.length; i++) {
        	//counting the minterms .. if any undefined char -#@$- found it will throw an exception handled on Submit action function 
            if ((Character.getNumericValue(arr[i]) > -1 && Character.getNumericValue(arr[i]) < 10) || arr[i] == ',') {  
            	str.append(arr[i]);
                if (arr[i] == ',') {
                    mtnum++;

                }
                if(i==arr.length-1){
                    mtnum++;
                }
            }else{
            	throw null;
            }
        }
        for (int i = 0; i < arr2.length; i++) { // same with don'tcares
            if ((Character.getNumericValue(arr2[i]) > -1 && Character.getNumericValue(arr2[i]) < 10)
                    || arr2[i] == ',') {
                str2.append(arr2[i]);
                if (arr2[i] == ',') {
                    dcnum++;

                }
                if(i==arr2.length-1){
                    dcnum++;
                }
            }
        }


        StringBuilder str5 = new StringBuilder();
        str5.append(str2); // saving don't cares in str5 to use them later
        /*
         * get the maximum number in minterms and don't cares to get the number of variables
         */
        int maxofmin = getmaxint(str, mtnum);
        int maxofdc = getmaxint(str2, dcnum);
        int totmax = maxofmin > maxofdc ? maxofmin : maxofdc;
        double twopowerx = Math.log(totmax + 1) / Math.log(2);
        this.varsnum = (int) Math.ceil(twopowerx);
        stepsstr.append("Variables number : " + this.varsnum + "\n==============================\n");
        if(fbool){
            try{
        	    	writer.print("Variables number : " + this.varsnum );
        	    	writer.println();
        	    	writer.print("==============================");
        	    	writer.println();
        	    
        	} catch (Exception e) {
        	   throw null;
        	}
            }
        // ======================================================================================================================
        
        
        // ----second section converting the minterms and dontcares to 2d array -of binary representation -called minterms---
        char[][] minterms = new char[mtnum + dcnum][varsnum];
        if (dcnum != 0) {
            toarray(minterms, str2, 0, dcnum);// to array
        }
        toarray(minterms, str, dcnum, dcnum + mtnum);
        // ==================================================================================================================================

        
        // --third section ..creating a hash map to group minterms and dc 
        HashMap<Integer, LinkedList> grouping = new HashMap<Integer, LinkedList>();

        int numberofones = 0;
        String h;

        for (int p = 0; p <= varsnum; p++) {
            LinkedList<elem> container = new LinkedList<elem>();

            int flag12 = 0;
            for (int i = 0; i < minterms.length; i++) {

                numberofones = getnumberofones(minterms, i); // get thenumber of ones
                h = makestring(minterms, i); // convert to string
                if (numberofones == p) {
                    elem e1 = new elem(h, new Integer(Integer.parseInt(h, 2)).toString() + ",");
                    stepsstr.append(e1.nums + "\t" + e1.str + "\n");
                    if(fbool){
                        try{
                    	    	writer.print(e1.nums + "\t" + e1.str );
                    	    	writer.println();
                    	    	
                    	    
                    	} catch (Exception e) {
                    	   throw null;
                    	}
                        }
                    container.add(e1);
                    flag12++;
                }
            }
            if (flag12 != 0) {
                stepsstr.append("__________________________\n");
                if(fbool){
                    try{
                	    	writer.print("__________________________" );
                	    	writer.println();
                	    	
                	    
                	} catch (Exception e) {
                	   throw null;
                	}
                    }
                grouping.put(p, container);
                flag12 = 0;
            }

        }
        stepsstr.append("\n=============================== \n");
        if(fbool){
            try{
    	    	writer.println();
        	    	writer.print("===============================" );
        	    	writer.println();
        	    	
        	    
        	} catch (Exception e) {
        	   throw null;
        	}
            }
        // ===============================================================================================================

        
        // ---- last section in part one... comparing
        LinkedList<elem> result = new LinkedList<elem>();
        int stopflag = 1;
        while (stopflag != 0) {
            stopflag = 0;

            LinkedList<elem> first = new LinkedList<elem>();
            LinkedList<elem> second = new LinkedList<elem>();

            for (int i = 0; i < varsnum; i++) { // looping in the map keys

                if (grouping.containsKey(i)) { // only in case of existing keys
                    int currentKey = i;
                    int nextKey = -1;

                    for (int w = i + 1; w <= varsnum; w++) { // looping to get
                        // the next key
                        // -->remains -1
                        // if not found
                        if (grouping.containsKey(w)) {
                            nextKey = w;
                            break;
                        }
                    }
                  

                    if (nextKey != -1) { // only in case of next key exists

                        first = grouping.get(currentKey);
                        second = grouping.get(nextKey);
                        LinkedList<elem> newll = new LinkedList<elem>();

                        for (int n = 0; n < first.size(); n++) { // in linked
                            // fist
                            // &second
                            for (int m = 0; m < second.size(); m++) {

                                String strr1 = new String();
                                String strr2 = new String();
                                strr1 = first.get(n).str;
                                strr2 = second.get(m).str;
                                int flagc = 1;
                                int indexx = 0;

                                for (int x = 0; x < strr1.length(); x++) { // in
                                    // str

                                    if (strr1.charAt(x) == '-' && strr2.charAt(x) != '-') {
                                        flagc = 4;
                                    } else if (strr1.charAt(x) != strr2.charAt(x)) {
                                        flagc++;
                                        indexx = x;
                                    }

                                }

                                if (flagc == 2) {
                                    first.get(n).found = true;
                                    second.get(m).found = true;
                                    stopflag++;
                                    char[] U = strr1.toCharArray();
                                    U[indexx] = '-';
                                    strr1 = makestring1d(U);
                                    elem e2 = new elem(strr1, first.get(n).nums + second.get(m).nums);
                                    newll.add(e2);
                                }
                            }
                            if (first.get(n).found == false) {
                                result.add(first.get(n));
                            }
                        }

                        grouping.remove(i);
                        if (stopflag != 0) {
                            for (int countqq = 0; countqq < newll.size(); countqq++) {
                                stepsstr.append(newll.get(countqq).nums + "\t" + newll.get(countqq).str + "\n");
                                if(fbool){
                                    try{
                            	    	writer.println();
                                	    	writer.print(newll.get(countqq).nums + "\t" + newll.get(countqq).str );
                                	    	writer.println();
                                	    	
                                	    
                                	} catch (Exception e) {
                                	   throw null;
                                	}
                                    }
                            }
                            grouping.put(i, newll);
                        }

                    }else{
                    	first = grouping.get(currentKey);
                    	 for (int n = 0; n < first.size(); n++) {
                    		 if (first.get(n).found == false) {
                                 result.add(first.get(n));
                             }
                    	 }
                    }

                }
            }
        }
        stepsstr.append("\n============================ \n");
        if(fbool){
            try{
    	    	writer.println();
        	    	writer.print("===============================" );
        	    	writer.println();
        	    	
        	    
        	} catch (Exception e) {
        	   throw null;
        	}
            }
        // ===================================================================================
        
        for (int j = 0; j < result.size(); j++) {
            for (int i = j + 1; i < result.size(); i++) {
                if (compare(result.get(j).str, result.get(i).str)) {
                    result.remove(i);
                }
            }
        }

        for (int i = 0; i < result.size(); i++) { // removing dontcares
            String arrayres[] = result.get(i).nums.split(",");
            String arraydc[] = str5.toString().split(",");
            for (int m = 0; m < arrayres.length; m++) {
                for (int n = 0; n < arraydc.length; n++) {
                    if (arrayres[m].equals(arraydc[n])) {
                        arrayres[m] = "-";
                    }
                }
            }
            StringBuilder sw = new StringBuilder();
            for (int m = 0; m < arrayres.length; m++) {
                if (arrayres[m] != "-") {
                    sw.append(arrayres[m]);
                    if (m != arrayres.length - 1) {
                        sw.append(",");
                    }
                }
            }
            result.get(i).nums = sw.toString();
        } 
        String[][] array1 = new String[result.size()][2];//
        modresult(result, array1);
        LinkedList<elem2> result2 = new LinkedList<elem2>();
        for (int i = 0; i < array1.length; i++) {

            stepsstr.append("\n______________________________\n");
            if(fbool){
                try{
        	    	writer.println();
            	    	writer.print("______________________________" );
            	    	writer.println();
            	    	
            	    
            	} catch (Exception e) {
            	   throw null;
            	}
                }
            stepsstr.append(array1[i][1] + "\t" + array1[i][0]);
            if(fbool){
                try{
            	    	writer.print(array1[i][1] + "\t" + array1[i][0] );
            	    	
            	    
            	} catch (Exception e) {
            	   throw null;
            	}
                }
        }
        stepsstr.append("\n====================================== \n");
        if(fbool){
            try{
    	    	writer.println();
        	    	writer.print("===============================" );
        	    	writer.println();
        	    	
        	    
        	} catch (Exception e) {
        	   throw null;
        	}
            }
        result2 = makeresult2(result);
        prepare(result2);
        StringBuilder randprimeandess = new StringBuilder();	 //final result string 
        
        for (int i = 0; i < prime.size(); i++) {
            for (int j = 0; j < prime.get(i).size(); j++) {
            	if(prime.get(i).get(j)==null){ // at some cases you gave me empty list .. this condition ignores it
            		continue;
            	}
                stepsstr.append("primes  :" + prime.get(i).get(j) + "\n");
                if(fbool){
                    try{
            	   
                	    	writer.print("primes  :" + prime.get(i).get(j) );
                	    	writer.println();
                	    	
                	    
                	} catch (Exception e) {
                	   throw null;
                	}
                    }
            	
                if (i == 0) {  // put the first primes' list in final result string 
                    randprimeandess.append(prime.get(i).get(j) + "+");
                }
                
            }
            if (i != prime.size() - 1) {
                stepsstr.append("\nOR:\n");
                if(fbool){
                    try{
                    	writer.println();
                	    	writer.print("OR" );
                	    	writer.println();
                	    	
                	    
                	} catch (Exception e) {
                	   throw null;
                	}
                    }
               
            }
        }
        if (randprimeandess.length() != 0) {
            randprimeandess.deleteCharAt(randprimeandess.length() - 1);
            stepsstr.append("\n_____________________________\n");
            if(fbool){
                try{
        	    	writer.println();
            	    	writer.print("______________________________" );
            	    	writer.println();
            	    	
            	    
            	} catch (Exception e) {
            	   throw null;
            	}
                }
        }
        for (int j = 0; j < this.essential.size(); j++) {
            stepsstr.append("\n______________________________\n");
            if(fbool){
                try{
        	    	writer.println();
            	    	writer.print("______________________________" );
            	    	writer.println();
            	    	
            	    
            	} catch (Exception e) {
            	   throw null;
            	}
                }
            stepsstr.append("essential  " + essential.get(j));
            if(fbool){
                try{
            	    	writer.print("essential  " + essential.get(j));
            	   
            	    	
            	    
            	} catch (Exception e) {
            	   throw null;
            	}
                }
        }
        if(mtnum+dcnum==Math.pow(2, this.varsnum)||randprimeandess.length()==0){
        	randprimeandess.append("1");
        }
        stepsstr.append("\n****************************************************** \n    RESULT :"+ randprimeandess.toString() + "\n******************************************************");
        if(fbool){
            try{
            	writer.println();
            	writer.println();
            	writer.println();
        	    	writer.print("RESULT :"+ randprimeandess.toString());        	    
        	} catch (Exception e) {
        	   throw null;
        	}
            }
        /*
         * if show steps is true : print the steps
         * then print the final result 
         */
        if (sbool) {
            this.stepso.setText(stepsstr.toString());
        }
        
       
        try{// cosing file
    	    writer.close();
    	} catch (Exception e) {
    	   throw null;
    	}
        
        
        this.finalresult.setText(randprimeandess.toString());
        
        /*
         * reset all global variables 
         */
        this.varsnum = 0;
        this.prime.clear();
        this.essential.clear();
    }

    void Deletcolumn(String h, HashMap<Integer, LinkedList<String>> top, LinkedList<elem2> result) {
        LinkedList<Integer> ar = new LinkedList<Integer>();

        for (int i = 0; i < result.size(); i++) {

            if (compare(result.get(i).str, h)) {
                ar = result.get(i).nums;
                result.remove(i);
                break;
            }
        }
        for (int i = 0; i < ar.size(); i++) {
            top.remove(ar.get(i));

        }

    }

    void findes (HashMap<Integer, LinkedList<String>> top, LinkedList<elem2> result, LinkedList<Integer> topcol) {
        for (int i = 0; i < result.size(); i++) {
            LinkedList<Integer> ar = result.get(i).nums;

            for (int j = 0; j < ar.size(); j++) {
                LinkedList<String> k = new LinkedList<String>();
                int s = ar.get(j);

                if (top.containsKey(s) && top.get(s).size() != 0) {
                    k = top.get(s);
                    k.add(result.get(i).str);

                } else
                    k.add(result.get(i).str);

                top.put(s, k);
            }
        }
        HashMap.Entry<Integer, LinkedList<String>> entry = null;

        for (Iterator<HashMap.Entry<Integer, LinkedList<String>>> it = top.entrySet().iterator(); it.hasNext();) {
            entry = it.next();

            if (entry.getValue().size() == 1) {

                essential.add(entry.getValue().get(0));
                Deletcolumn(entry.getValue().get(0), top, result);
                if (it.hasNext()) {
                    it = top.entrySet().iterator();
                }

            }

        }

        for (HashMap.Entry<Integer, LinkedList<String>> entery : top.entrySet())
            topcol.add(entery.getKey());

    }

    boolean[][] covering(LinkedList<elem2> result) {
        boolean ar[][] = new boolean[result.size()][10000];

        for (int i = 0; i < result.size(); i++) {
            for (int j = 0; j < result.get(i).nums.size(); j++) {
                ar[i][result.get(i).nums.get(j)] = true;

            }
        }
        return ar;

    }

    void prepare(LinkedList<elem2> result) {
        HashMap<Integer, LinkedList<String>> top = new HashMap<Integer, LinkedList<String>>();
        LinkedList<String> temp = new LinkedList<String>();

        LinkedList<Integer> topcol = new LinkedList<Integer>();

        findes(top, result, topcol);
        boolean cover[][] = covering(result);
        completesearch(0, topcol, result, temp, cover);
        int counter = 0;
        char abc[] = new char[28];
        for (int i = 65; i <= 90; i++) {
            abc[counter++] = (char) i;

        }
        for (int i = 0; i < prime.size(); i++) {
            if (prime.get(i).size() > min + essential.size()) {
                prime.remove(i);
                i = -1;

            }

        }
        if(prime.size()==0){
            LinkedList<String> tempp=new LinkedList<String>();
            for(int i=0;i<essential.size();i++){
                tempp.add(essential.get(i));



            }
            prime.add(tempp);
        }
        convert_to_string(abc);
        convert_to_string2(abc);

    }

    void remove(char[] v, int x) {
        for (int i = x; i < v.length - 1; i++)
            v[i] = v[i + 1];
    }

    void convert_to_string2(char[] abc) {

        for (int j = 0; j < essential.size(); j++) {
            String exp = essential.get(j);
            char v[] = new char[1000];
            v = exp.toCharArray();
            int counter = exp.length();
            StringBuilder l = new StringBuilder();

            for (int x = 0; x < exp.length(); x++) {
                if (v[x] == '-') {

                    continue;
                } else if (v[x] == '0') {
                    l.append(abc[x]);
                    l.append('\'');
                    counter++;
                } else
                    l.append(abc[x]);

            }
            char v2[] = new char[l.length()];
            int c = 0;
            for (int k = 0; k < l.length(); k++) {
                v2[k] = l.charAt(k);
            }
            String p = String.valueOf(v2);
            essential.remove(j);
            essential.add(j, p);

        }

    }

    void convert_to_string(char[] abc) {
        for (int i = 0; i < prime.size(); i++) {
            LinkedList<String> p = new LinkedList<String>();
            for (int j = 0; j < prime.get(i).size(); j++) {
                String exp = prime.get(i).get(j);
                char v[] = new char[1000];
                v = exp.toCharArray();
                int counter = exp.length();
                StringBuilder l = new StringBuilder();

                for (int x = 0; x < exp.length(); x++) {
                    if (v[x] == '-') {

                        continue;
                    } else if (v[x] == '0') {
                        l.append(abc[x]);
                        l.append('\'');
                        counter++;
                    } else
                        l.append(abc[x]);

                }
                char v2[] = new char[l.length()];
                int c = 0;
                for (int k = 0; k < l.length(); k++) {
                    v2[k] = l.charAt(k);
                }
                p.add(String.valueOf(v2));

            }
            prime.remove(i);
            prime.add(i, p);

        }
    }

    void checkcovering(LinkedList<Integer> topcol, LinkedList<Integer> topcoltemp, boolean cover[][], int index) {
        for (int i = 0; i < topcol.size(); i++) {
            if (cover[index][topcol.get(i)] == true) {
                topcoltemp.add(topcol.get(i));
                topcol.remove(i);
                i=-1;
            }
        }

    }

    void addessential(LinkedList<String> temp, LinkedList<String> temp2) {
        for (int i = 0; i < essential.size(); i++) {
            temp.add(essential.get(i));
        }
        for (int i = 0; i < temp2.size(); i++) {
            temp.add(temp2.get(i));
        }

    }

    void backcol(LinkedList<Integer> topcol, LinkedList<Integer> topcoltemp, int i) {
        for (int x = 0; x < i; x++) {
            if (topcoltemp.size() != 0) {
                topcol.add(topcoltemp.getLast());
                topcoltemp.removeLast();
            }
        }
    }

    void completesearch(int index, LinkedList<Integer> topcol, LinkedList<elem2> result, LinkedList<String> temp,
            boolean[][] cover) {
        if (topcol.size() == 0) {
            if (temp.size() != 0) {
                if (temp.size() <= min) {

                    min = temp.size();
                    LinkedList<String> temp2 = new LinkedList<String>();

                    addessential(temp2, temp);
                    prime.add(temp2);
                }
            }
            return;
        }
        for (int i = index; i < result.size(); i++) {
            LinkedList<Integer> topcoltemp = new LinkedList<Integer>();
            temp.add(result.get(i).str);
            int prev = topcol.size();
            checkcovering(topcol, topcoltemp, cover, i);
            if (topcoltemp.size() != 0)
                completesearch(i + 1, topcol, result, temp, cover);
            temp.removeLast();
            backcol(topcol, topcoltemp, prev - topcol.size());

        }

    }
}
