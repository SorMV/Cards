package Cards;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Миша on 19.06.2018.
 */
public class QuizCardBuilder {

    private JTextArea question;
    private JTextArea answer;
    private ArrayList<QuizCard> quizCards;
    private JFrame frame;

    public void go() {
        frame=new JFrame("QuizCard Builder");
        quizCards=new ArrayList<QuizCard>();
        JPanel panel=new JPanel();
        question=new JTextArea(6, 20);
        question.setLineWrap(true);
        question.setWrapStyleWord(true);
        JScrollPane scroll=new JScrollPane(question);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        answer=new JTextArea(6, 20);
        answer.setLineWrap(true);
        answer.setWrapStyleWord(true);
        JScrollPane scroll2=new JScrollPane(answer);
        scroll2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroll2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        JButton button=new JButton("Next");
        button.addActionListener(new nextListener());
        JLabel label1=new JLabel("Question");
        JLabel label2=new JLabel("Answer");


        JMenuBar jmb=new JMenuBar();
        JMenu menu = new JMenu("File");
        JMenuItem noob=new JMenuItem("New");
        JMenuItem save=new JMenuItem("Save");
        save.addActionListener(new SaveListener());
        noob.addActionListener(new newListener());
        menu.add(noob);
        menu.add(save);
        jmb.add(menu);
        frame.setJMenuBar(jmb);
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.add(label1);
        panel.add(question);
        panel.add(button);
        panel.add(label2);
        panel.add(answer);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(panel);
        frame.setSize(300,500);
        frame.setVisible(true);

    }


    private class nextListener implements  ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            QuizCard card=new QuizCard(question.getText(), answer.getText());
            quizCards.add(card);
            clearCard();
        }
    }


    private class SaveListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            QuizCard card = new QuizCard(question.getText(), answer.getText());
            quizCards.add(card);
            JFileChooser chooser=new JFileChooser();
            chooser.showSaveDialog(frame);
            saveFile(chooser.getSelectedFile());
        }
    }

    private class newListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            quizCards.clear();
            clearCard();
        }
    }

    private void clearCard() {
        question.setText("");
        answer.setText("");
        question.requestFocus();
    }


    public void saveFile(File file) {
        try {
            BufferedWriter buf=new BufferedWriter(new FileWriter(file));
            for(QuizCard card: quizCards) {
                buf.write(card.getQuestion()+ "/");
                buf.write(card.getAnswer()+ "\n");
            }
            buf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        QuizCardBuilder qzb=new QuizCardBuilder();
        qzb.go();
    }

}
