package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.Re.Recipy;

public class RecipyModel {
	Connection con;

	public RecipyModel() throws Exception {
		con = DBcon.getConnection();
	}

	// recnum, genre, jlname, jltime, jling, jltool, jlrec
	public void insertRecipy(Recipy re) throws Exception {
		String sql = "insert into recipy2 values (seq_re_num.nextval, ?,?,?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, re.getGENRE());
		ps.setString(2, re.getJLNAME());
		ps.setInt(3, re.getJLTIME());
		ps.setString(4, re.getJLING());
		ps.setString(5, re.getJLTOOL());
		ps.setString(6, re.getJLREC());

		int r = ps.executeUpdate();
		if (r == 1) {
		}
		ps.close();
	}

	public ArrayList searchRecipy(int idx, String str) throws Exception {
		String[] key = { "JLNAME", "GENRE" };
		String sql = "select * from recipy2 where " + key[idx] + " like '%" + str + "%' order by recnum";
		PreparedStatement ps = con.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();

		ArrayList data = new ArrayList();
		while (rs.next()) {
			ArrayList temp = new ArrayList();
			temp.add(rs.getString("recnum"));
			temp.add(rs.getString("JLNAME"));
			temp.add(rs.getString("JLTIME"));
			temp.add(rs.getString("GENRE"));
			temp.add(rs.getString("JLING"));
			temp.add(rs.getString("JLTOOL"));

			data.add(temp);
		}

		rs.close();
		ps.close();

		return data;
	}

	public Recipy selectbyPk(int no) throws Exception {
		Recipy re = new Recipy();
		String sql = "select * from Recipy2 " + "where recnum=" + no;
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			re.setRecnum(Integer.parseInt(rs.getString("recnum")));
			re.setJLNAME(rs.getString("jlName"));
			re.setGENRE(rs.getString("genre"));
			re.setJLTIME(Integer.parseInt(rs.getString("jlTime")));
			re.setJLING(rs.getString("jlIng"));
			re.setJLTOOL(rs.getString("jlTool"));
			re.setJLREC(rs.getString("jlRec"));
		}
		rs.close();
		ps.close();
		return re;
	}
	// recnum, genre, jlname, jltime, jling, jltool, jlrec
	public void modifyRecipy(Recipy re) throws SQLException {
		String sql = "update RECIPY2 set " + "genre = ?" + ",jltime = ?" + ",jling = ?" + ",jltool = ?" + ",jlrec = ? "
				+ "where jlname = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, re.getGENRE());
		ps.setInt(2, re.getJLTIME());
		ps.setString(3, re.getJLING());
		ps.setString(4, re.getJLTOOL());
		ps.setString(5, re.getJLREC());
		ps.setString(6, re.getJLNAME());

		ps.executeUpdate();

		ps.close();

	}

	public void deleteRecipy(Recipy re) throws Exception {
		con.setAutoCommit(false);
		String sql = "delete from Recipy2 where recnum = ? ";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, re.getRecnum());

		int r = ps.executeUpdate();
		
		con.commit();
		
		ps.executeUpdate();

		ps.close();
		con.setAutoCommit(true);

	}

}
