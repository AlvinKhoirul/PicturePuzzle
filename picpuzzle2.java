import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

class PicPuzzle2 extends JFrame implements ActionListener {
    private JButton b1, b2, b3, b4, b5, b6, b7, b8, b9, sample;
    private Timer timer;
    private int hours, minutes, seconds;
    private JLabel timerLabel;
    private JButton finishButton, stopButton;
    private boolean isGameFinished;
    private Icon star;
    private Icon samicon1 = new ImageIcon("R.jpg");
    private Icon samicon2 = new ImageIcon("S.png");
    private Icon samicon3 = new ImageIcon("T.jpg");
    private Icon ic1 = new ImageIcon("R1.jpg");
    private Icon ic2 = new ImageIcon("R5.jpg");
    private Icon ic3 = new ImageIcon("R2.jpg");
    private Icon ic4 = new ImageIcon("R7.jpg");
    private Icon ic5 = new ImageIcon("R4.jpg");
    private Icon ic6 = new ImageIcon("R6.jpg");
    private Icon ic7 = new ImageIcon("R8.jpg");
    private Icon ic8 = new ImageIcon("R9.jpg");
    private Icon ic9;

    private Icon ic11 = new ImageIcon("S0.jpg");
    private Icon ic12 = new ImageIcon("S3.jpg");
    private Icon ic13 = new ImageIcon("S6.jpg");
    private Icon ic14 = new ImageIcon("S1.jpg");
    private Icon ic15 = new ImageIcon("S4.jpg");
    private Icon ic16 ;
    private Icon ic17 = new ImageIcon("S7.jpg");
    private Icon ic18 = new ImageIcon("S5.jpg");
    private Icon ic19 = new ImageIcon("S8.jpg")
    ;

    private Icon ic21 = new ImageIcon("T4.jpg");
    private Icon ic22 = new ImageIcon("T5.jpg");
    private Icon ic23 = new ImageIcon("T1.jpg");
    private Icon ic24 = new ImageIcon("T7.jpg");
    private Icon ic25 = new ImageIcon("T3.jpg");
    private Icon ic26 ;
    private Icon ic27 = new ImageIcon("T8.jpg");
    private Icon ic28 = new ImageIcon("T2.jpg");
    private Icon ic29 = new ImageIcon("T6.jpg");

    PicPuzzle2() {
        super("Picture Puzzle By Alvin Khoirul");

        hours = 0;
        minutes = 0;
        seconds = 0;

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel Puzzle
        JPanel puzzlePanel = new JPanel();
        puzzlePanel.setLayout(new GridLayout(3, 3, 5, 5));
        puzzlePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        b1 = new JButton(ic1);
        b1.addActionListener(this);
        puzzlePanel.add(b1);

        // Add other buttons (b2 to b9) to puzzlePanel
        b2 = new JButton(ic2);
        b2.addActionListener(this);
        puzzlePanel.add(b2);

        b3 = new JButton(ic3);
        b3.addActionListener(this);
        puzzlePanel.add(b3);

        b4 = new JButton(ic4);
        b4.addActionListener(this);
        puzzlePanel.add(b4);

        b5 = new JButton(ic5);
        b5.addActionListener(this);
        puzzlePanel.add(b5);

        b6 = new JButton(ic6);
        b6.addActionListener(this);
        puzzlePanel.add(b6);

        b7 = new JButton(ic7);
        b7.addActionListener(this);
        puzzlePanel.add(b7);

        b8 = new JButton(ic8);
        b8.addActionListener(this);
        puzzlePanel.add(b8);

        b9 = new JButton(ic9);
        b9.addActionListener(this);
        puzzlePanel.add(b9);

        mainPanel.add(puzzlePanel, BorderLayout.CENTER);

        // Add buttons (b1 to b9) to the frame with specified bounds
        b1.setBounds(30, 80, 100, 100);
        b2.setBounds(130, 80, 100, 100);
        b3.setBounds(230, 80, 100, 100);
        b4.setBounds(30, 180, 100, 100);
        b5.setBounds(130, 180, 100, 100);
        b6.setBounds(230, 180, 100, 100);
        b7.setBounds(30, 280, 100, 100);
        b8.setBounds(130, 280, 100, 100);
        b9.setBounds(230, 280, 100, 100);

        // Add buttons to the frame
        add(b1);
        add(b2);
        add(b3);
        add(b4);
        add(b5);
        add(b6);
        add(b7);
        add(b8);
        add(b9);

        sample = new JButton(samicon1);
        sample.setBounds(380, 100, 200, 200);

        JLabel l1 = new JLabel("Sample:");
        l1.setBounds(330, 200, 70, 20);

        // Panel Control
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BorderLayout());
        controlPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

        sample = new JButton(samicon1);
        sample.addActionListener(this);
        controlPanel.add(sample, BorderLayout.CENTER);

        finishButton = new JButton("Selesai");
        finishButton.addActionListener(this);
        controlPanel.add(finishButton, BorderLayout.SOUTH);

        stopButton = new JButton("Stop"); 
        stopButton.addActionListener(this);
        controlPanel.add(stopButton, BorderLayout.NORTH); 

        mainPanel.add(controlPanel, BorderLayout.EAST);

        // Panel Informasi
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        infoPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        timerLabel = new JLabel("Timer: 00:00:00");
        infoPanel.add(timerLabel);

        JLabel l3 = new JLabel("Click sample picture to next puzzle");
        l3.setForeground(Color.red);
        infoPanel.add(l3);

        mainPanel.add(infoPanel, BorderLayout.SOUTH);

        // Frame Utama
        setLayout(new BorderLayout());
        add(mainPanel);

        setSize(620, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Menempatkan frame di tengah layar
        setResizable(false);
        setVisible(true);

        startTimer();
        isGameFinished = false;
    }

    private void updateTimer() {
        seconds++;
        if (seconds == 60) {
            seconds = 0;
            minutes++;
            if (minutes == 60) {
                minutes = 0;
                hours++;
            }
        }
        updateTimerLabel();
    }

    private void updateTimerLabel() {
        String formattedTime = String.format("Timer: %02d:%02d:%02d", hours, minutes, seconds);
        timerLabel.setText(formattedTime);
    }

    private void startTimer() {
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTimer();
            }
        });
        timer.start();
    }

    private void stopTimer() {
        if (timer != null && timer.isRunning()) {
            timer.stop();
        }
    }

    public void actionPerformed(ActionEvent e) {
      if (e.getSource() == b1) {
          Icon s1 = b1.getIcon();
          if (b2.getIcon() == star) {
              b2.setIcon(s1);
              b1.setIcon(star);
          } else if (b4.getIcon() == star) {
              b4.setIcon(s1);
              b1.setIcon(star);
          }
      } else if (e.getSource() == b2) {
          Icon s1 = b2.getIcon();
          if (b1.getIcon() == star) {
              b1.setIcon(s1);
              b2.setIcon(star);
          } else if (b5.getIcon() == star) {
              b5.setIcon(s1);
              b2.setIcon(star);
          } else if (b3.getIcon() == star) {
              b3.setIcon(s1);
              b2.setIcon(star);
          }
      } else if (e.getSource() == b3) {
          Icon s1 = b3.getIcon();
          if (b2.getIcon() == star) {
              b2.setIcon(s1);
              b3.setIcon(star);
          } else if (b6.getIcon() == star) {
              b6.setIcon(s1);
              b3.setIcon(star);
          }
      } else if (e.getSource() == b4) {
          Icon s1 = b4.getIcon();
          if (b1.getIcon() == star) {
              b1.setIcon(s1);
              b4.setIcon(star);
          } else if (b5.getIcon() == star) {
              b5.setIcon(s1);
              b4.setIcon(star);
          } else if (b7.getIcon() == star) {
              b7.setIcon(s1);
              b4.setIcon(star);
          }
      } else if (e.getSource() == b5) {
          Icon s1 = b5.getIcon();
          if (b2.getIcon() == star) {
              b2.setIcon(s1);
              b5.setIcon(star);
          } else if (b4.getIcon() == star) {
              b4.setIcon(s1);
              b5.setIcon(star);
          } else if (b6.getIcon() == star) {
              b6.setIcon(s1);
              b5.setIcon(star);
          } else if (b8.getIcon() == star) {
              b8.setIcon(s1);
              b5.setIcon(star);
          }
      } else if (e.getSource() == b6) {
          Icon s1 = b6.getIcon();
          if (b3.getIcon() == star) {
              b3.setIcon(s1);
              b6.setIcon(star);
          } else if (b5.getIcon() == star) {
              b5.setIcon(s1);
              b6.setIcon(star);
          } else if (b9.getIcon() == star) {
              b9.setIcon(s1);
              b6.setIcon(star);
          }
      } else if (e.getSource() == b7) {
          Icon s1 = b7.getIcon();
          if (b4.getIcon() == star) {
              b4.setIcon(s1);
              b7.setIcon(star);
          } else if (b8.getIcon() == star) {
              b8.setIcon(s1);
              b7.setIcon(star);
          }
      } else if (e.getSource() == b8) {
          Icon s1 = b8.getIcon();
          if (b7.getIcon() == star) {
              b7.setIcon(s1);
              b8.setIcon(star);
          } else if (b5.getIcon() == star) {
              b5.setIcon(s1);
              b8.setIcon(star);
          } else if (b9.getIcon() == star) {
              b9.setIcon(s1);
              b8.setIcon(star);
          }
      } else if (e.getSource() == b9) {
          Icon s1 = b9.getIcon();
          if (b8.getIcon() == star) {
              b8.setIcon(s1);
              b9.setIcon(star);
          } else if (b6.getIcon() == star) {
              b6.setIcon(s1);
              b9.setIcon(star);
          }
      } else if (e.getSource() == sample) {
          Icon s1 = sample.getIcon();
          if (s1 == samicon3) {
              sample.setIcon(samicon1);
              b1.setIcon(ic1);
              b2.setIcon(ic2);
              b3.setIcon(ic3);
              b4.setIcon(ic4);
              b5.setIcon(ic5);
              b6.setIcon(ic6);
              b7.setIcon(ic7);
              b8.setIcon(ic8);
              b9.setIcon(ic9);
              star = b9.getIcon();
          } else if (s1 == samicon1) {
              sample.setIcon(samicon2);
              b1.setIcon(ic11);
              b2.setIcon(ic12);
              b3.setIcon(ic13);
              b4.setIcon(ic14);
              b5.setIcon(ic15);
              b6.setIcon(ic16);
              b7.setIcon(ic17);
              b8.setIcon(ic18);
              b9.setIcon(ic19);
              star = b6.getIcon();
          } else {
              sample.setIcon(samicon3);
              b1.setIcon(ic21);
              b2.setIcon(ic22);
              b3.setIcon(ic23);
              b4.setIcon(ic24);
              b5.setIcon(ic25);
              b6.setIcon(ic26);
              b7.setIcon(ic27);
              b8.setIcon(ic28);
              b9.setIcon(ic29);
              star = b6.getIcon();
          }
      } else if (e.getSource() == finishButton) {
        stopTimer();
        JOptionPane.showMessageDialog(null, "Selamat! Anda menyelesaikan puzzle dalam waktu " + hours + ":" + minutes + ":" + seconds);
        isGameFinished = true;
        if (!isGameFinished) {
            updateTimerLabel(); // Call the method to update timer values
            JOptionPane.showMessageDialog(null, "Permainan belum selesai. Waktu yang telah berlalu: " + hours + ":" + minutes + ":" + seconds);
        } else {
            hours = 0;
            minutes = 0;
            seconds = 0;
            isGameFinished = false;
            startTimer();
        }
    } else if (e.getSource() == stopButton) {
      stopTimer();
      JOptionPane.showMessageDialog(null, "Permainan dihentikan. Waktu yang telah berlalu: " + hours + ":" + minutes + ":" + seconds);
      isGameFinished = true;
      if (!isGameFinished) {
          updateTimerLabel(); // Call the method to update timer values
          JOptionPane.showMessageDialog(null, "Permainan belum selesai. Waktu yang telah berlalu: " + hours + ":" + minutes + ":" + seconds);
      } else {
          hours = 0;
          minutes = 0;
          seconds = 0;
          isGameFinished = false;
          startTimer();
      }
  }
    
  }  

  public static void main(String args[]) {
    SwingUtilities.invokeLater(new Runnable() {
        public void run() {
            new PicPuzzle2();
        }
    });
}
}