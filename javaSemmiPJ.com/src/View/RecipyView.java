package View;

//�˻����� ����
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;

import Model.RecipyModel;
import Model.Re.Recipy;

public class RecipyView extends Panel implements ActionListener {
	JTextField tfName, tfTime, tfIng, tfTool, tfrecnum;
	JComboBox cbJenre;
	JTextArea taContent;

	JTextField tfInsertCount;

	JButton bRecipyInsert, bRecipyModify, bRecipyDelete;

	JComboBox comRecipySearch;
	JTextField tfRecipySearch;
	JTable tableRecipy;

	RecipyModel model;

	RecipyTableModel tbModelRecipy;

	JScrollPane sp2 = new JScrollPane(tfTool);
	
	public RecipyView() {
		addLayout();
		eventProc();
		connectDB();
		iniStyle();
	}

	void iniStyle() {
		tfrecnum.setEditable(false);
	}

	public void connectDB() {
		try {
			model = new RecipyModel();
			System.out.println("������ ���� ����");
		} catch (Exception e) {
			System.out.println("������ ���� ����");
			e.printStackTrace();
		}
	}

	public void eventProc() {
		bRecipyDelete.addActionListener(this);
		bRecipyInsert.addActionListener(this);
		bRecipyModify.addActionListener(this);
		tfRecipySearch.addActionListener(this);
		tableRecipy.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tableRecipy.getSelectedRow();
				int col = 0;
				String data = (String) tableRecipy.getValueAt(row, col);
				System.out.println(data);
				int no = Integer.parseInt(data);

				try {
					Recipy re = model.selectbyPk(no);
					selectbyPk(re);

				} catch (Exception e1) {
				}
			}
		});

	}

	private void addLayout() {
		tfName = new JTextField();
		tfTime = new JTextField();
		tfIng = new JTextField();
		tfTool = new JTextField();
		tfrecnum = new JTextField();

		String[] cbJanreStr = { "�ѽ�", "���", "�߽�", "�Ͻ�" };
		cbJenre = new JComboBox(cbJanreStr);
		taContent = new JTextArea();

		tfInsertCount = new JTextField();

		bRecipyInsert = new JButton("�������߰�");
		bRecipyModify = new JButton("�����Ǽ���");
		bRecipyDelete = new JButton("�����ǻ���");

		String[] cbRecipySearch = {"�̸�", "�з�"};
		comRecipySearch = new JComboBox(cbRecipySearch);
		tfRecipySearch = new JTextField(15);

		tbModelRecipy = new RecipyTableModel();
		tableRecipy = new JTable(tbModelRecipy);
		tableRecipy.setModel(tbModelRecipy);

		// ȭ�鱸��
		// west �ǳ� ����
		JPanel p_west = new JPanel();
		p_west.setLayout(new BorderLayout());
		// ���� ���
		JPanel p_west_center = new JPanel();
		p_west_center.setLayout(new BorderLayout());
		// ���� ��� ����
		JPanel p_west_center_north = new JPanel();
		p_west_center_north.setLayout(new GridLayout(5, 2));
		p_west_center_north.add(new JLabel("�丮�̸�"));
		p_west_center_north.add(tfName);
		p_west_center_north.add(new JLabel("�з�"));
		p_west_center_north.add(cbJenre);
		p_west_center_north.add(new JLabel("�丮 �ð�"));
		p_west_center_north.add(tfTime);
		p_west_center_north.add(new JLabel("�丮���"));
		p_west_center_north.add(tfIng);
//		JScrollPane sp = new JScrollPane(tfIng);
		p_west_center_north.add(new JLabel("��Ÿ�ⱸ"));
		p_west_center_north.add(tfTool);
		// ���� ��� ��� ������ ����
		JPanel p_west_center_center = new JPanel();
		p_west_center_center.setLayout(new BorderLayout());
		p_west_center_center.add(new JLabel("����"), BorderLayout.WEST);
		p_west_center_center.add(taContent, BorderLayout.CENTER);
		taContent.setLineWrap(true);
		// ���� ��� �ǳڿ� �ΰ��� �ǳ� �߰�
		p_west_center.add(p_west_center_north, BorderLayout.NORTH);
		p_west_center.add(p_west_center_center, BorderLayout.CENTER);
		p_west_center.setBorder(new TitledBorder("������ ���� �Է�"));// ��輱 �����
		// ���� �Ʒ�
		JPanel p_west_south = new JPanel();
		p_west_south.setLayout(new GridLayout(1, 1));
		JPanel p_west_south_2 = new JPanel();
		p_west_south_2.setLayout(new GridLayout(1, 3));
		p_west_south_2.add(bRecipyInsert);
		p_west_south_2.add(bRecipyModify);
		p_west_south_2.add(bRecipyDelete);
		p_west_south.add(p_west_south_2);
		p_west.add(p_west_center, BorderLayout.CENTER);
		p_west.add(p_west_south, BorderLayout.SOUTH);
		// east �ǳ� ����
		JPanel p_east = new JPanel();
		p_east.setLayout(new BorderLayout());
		// �������� ���� �ǳ�
		JPanel p_east_north = new JPanel();
		p_east_north.add(comRecipySearch);
		p_east_north.add(tfRecipySearch);
		// ��輱 �����
		p_east_north.setBorder(new TitledBorder("������ �˻�"));
		p_east.add(p_east_north, BorderLayout.NORTH);
		p_east.add(new JScrollPane(tableRecipy), BorderLayout.CENTER);// 0618
		setLayout(new GridLayout(1, 2));
		add(p_west);
		add(p_east);
	}

	class RecipyTableModel extends AbstractTableModel {
		ArrayList data = new ArrayList();
		String[] columnNames = { "�����ǹ�ȣ", "�������̸�", "�丮 �ð�", "�з�", "�丮���", "��Ÿ�ⱸ" };

		@Override
		public int getColumnCount() {
			return columnNames.length;
		}

		@Override
		public int getRowCount() {
			return data.size();
		}

		@Override
		public Object getValueAt(int row, int col) {
			ArrayList temp = (ArrayList) data.get(row);
			return temp.get(col);
		}

		public String getColumnName(int col) {
			return columnNames[col];
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object evt = e.getSource();
		if (evt == bRecipyInsert) {
			insertRecipy();
		} else if (evt == tfRecipySearch) {
			searchRecipy();
		} else if (evt == bRecipyModify) {
			modifyRecipy();
		} else if (evt == bRecipyDelete) {
			deleteRecipy();
		}
	}

	private void deleteRecipy() {
		Recipy re = new Recipy();
		re.setRecnum(Integer.parseInt(tfrecnum.getText()));
		re.setJLNAME(tfName.getText());
		re.setJLTIME(Integer.parseInt(tfTime.getText()));
		re.setJLTOOL(tfTool.getText());
		re.setJLING(tfIng.getText());
		re.setJLREC(taContent.getText());
		re.setGENRE((String) cbJenre.getSelectedItem());
		try {
			model.deleteRecipy(re);
			JOptionPane.showMessageDialog(null, "�����Ϸ�");
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "��������");
		}
	}

	private void modifyRecipy() {
		Recipy re = new Recipy();
		re.setJLNAME(tfName.getText());
		re.setJLTIME(Integer.parseInt(tfTime.getText()));
		re.setJLTOOL(tfTool.getText());
		re.setJLING(tfIng.getText());
		re.setJLREC(taContent.getText());
		re.setGENRE((String) cbJenre.getSelectedItem());

		try {
			model.modifyRecipy(re);
			JOptionPane.showMessageDialog(null, "�����Ϸ�");
			tfName.setText(null);
			tfTool.setText(null);
			tfIng.setText(null);
			tfTime.setText(null);
			taContent.setText(null);
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, "��������");
		}
	}

	private void searchRecipy() {
		int idx = comRecipySearch.getSelectedIndex();
		String str = tfRecipySearch.getText();
		System.out.println("idx : " + idx);
		try {
			ArrayList data = model.searchRecipy(idx, str);
			tbModelRecipy.data = data;
			tableRecipy.setModel(tbModelRecipy);
			tbModelRecipy.fireTableDataChanged();
		} catch (Exception e1) {
		}
	}

	void selectbyPk(Recipy re) {
		tfrecnum.setText(String.valueOf(re.getRecnum()));
		tfTime.setText(String.valueOf(re.getJLTIME()));
		taContent.setText(re.getJLREC());
		tfTool.setText(re.getJLTOOL());
		tfName.setText(re.getJLNAME());
		tfIng.setText(re.getJLING());
		cbJenre.setSelectedItem(re.getGENRE());

	}

	private void insertRecipy() {
		Recipy re = new Recipy();

		re.setJLTIME(Integer.parseInt(tfTime.getText()));
		re.setGENRE((String) cbJenre.getSelectedItem());
		re.setJLING(tfIng.getText());
		re.setJLTOOL(tfTool.getText());
		re.setJLNAME(tfName.getText());
		re.setJLREC(taContent.getText());

		try {
			model.insertRecipy(re);
			JOptionPane.showMessageDialog(null, "�߰��Ϸ�");
			tfName.setText(null);
			tfTool.setText(null);
			tfIng.setText(null);
			tfTime.setText(null);
			taContent.setText(null);

		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "�߰�����");
			e1.printStackTrace();
		}
	}

}
