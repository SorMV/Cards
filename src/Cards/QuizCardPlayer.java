package Cards;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

/**
 * Created by Миша on 19.06.2018.
 */
public class QuizCardPlayer {
    private JTextArea question;
    private JTextArea answer;
    private ArrayList<QuizCard> quizCards;
    private JFrame frame;
    int flg=0;
    boolean flag=false;

    public void go() {
        frame = new JFrame("QuizCard Player");
        quizCards = new ArrayList<QuizCard>();
        JPanel panel = new JPanel();
        question = new JTextArea(6, 20);
        question.setLineWrap(true);
        question.setWrapStyleWord(true);
        JScrollPane scroll = new JScrollPane(question);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        answer = new JTextArea(6, 20);
        answer.setLineWrap(true);
        answer.setWrapStyleWord(true);
        JScrollPane scroll2 = new JScrollPane(answer);
        scroll2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroll2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);


        JButton button = new JButton("Show");
        button.addActionListener(new showListener());
        JLabel label1 = new JLabel("Question");
        JLabel label2 = new JLabel("Answer");


        JMenuBar jmb = new JMenuBar();
        JMenu menu = new JMenu("File");
        JMenuItem next = new JMenuItem("Next");
        JMenuItem open = new JMenuItem("Open");
        open.addActionListener(new openListener());
        next.addActionListener(new nextListener());
        menu.add(next);
        menu.add(open);
        jmb.add(menu);
        frame.setJMenuBar(jmb);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(label1);
        panel.add(question);
        panel.add(button);
        panel.add(label2);
        panel.add(answer);
        frame.setContentPane(panel);
        frame.setSize(500, 400);
        frame.setVisible(true);

    }

    private class nextListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(flag) {
                flg++;
                question.setText((quizCards.get(flg).getQuestion()));
                frame.repaint();
                answer.setText("");
            }
        }
    }


    private class showListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            answer.setText(quizCards.get(flg).getAnswer());
        }
    }


    private class openListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser chooser = new JFileChooser();
            chooser.showOpenDialog(frame);
            openFile(chooser.getSelectedFile());
            flag=true;
            question.setText((quizCards.get(flg).getQuestion()));
        }
    }

    public void openFile(File file) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String [] buffer;
            while (reader.ready()) {
                buffer=reader.readLine().split("/");
                if(buffer.length>=2) {
                    QuizCard card = new QuizCard(buffer[0], buffer[1]);
                    quizCards.add(card);
                }
            }
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        QuizCardPlayer qzb = new QuizCardPlayer();
        qzb.go();
    }
}

