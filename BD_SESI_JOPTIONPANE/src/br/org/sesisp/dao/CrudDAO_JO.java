package br.org.sesisp.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.mysql.jdbc.PreparedStatement;

import br.org.sesisp.controller.ConexaoJO;
import br.org.sesisp.model.AlunoJO;

public class CrudDAO_JO {
		
		// CRUD C-Create R-Read D-Delete
		
		//CREATE (Inserir dados)
		public void create(AlunoJO aluno) {
			String sql = "INSERT INTO alunos(nome, idade) VALUE (?,?)";
			Connection conn = null;
			PreparedStatement p = null;
			try {
				conn = ConexaoJO.CriandoConexao();
				p = (PreparedStatement) conn.prepareStatement(sql);
				p.setString(1, aluno.getNome());
				p.setInt(2, aluno.getIdade());
				p.execute();
			}catch(Exception e) {
				JOptionPane.showMessageDialog(null, "ERRO ao inserir dados! \nERRO: " + e);
			}finally {
				try {
					if(p != null);
					p.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			
	}// fim CRIATE
	//*****************************************************************
		
	//metodo U - Update
		
	public void update(AlunoJO aluno) {
		String sql = "UPDATE alunos SET nome = ?, idade = ? WHERE ra = ?";
		Connection conn = null;
		PreparedStatement p = null;
		try {
			conn = ConexaoJO.CriandoConexao();
			p = (PreparedStatement) conn.prepareStatement(sql);//cast
			p.setString(1, aluno.getNome());
			p.setInt(2, aluno.getIdade());
			p.setInt(3, aluno.getRa());
			p.execute();//executa instrução para gravar dados no banco
			JOptionPane.showMessageDialog(null, "Dados atualizados com sucesso");
			//System.out.println("Dados atualizados com sucesso");
		}catch(Exception e) {
			//JOptionPane.showMessageDialog(null, "ERRO ao atualizar dados! \nERRO: " + e);
		}finally {
			try {
				if(p != null);
				p.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
				
	}//fim UPDATE
	//*****************************************************************
	
	//metodo R - Read
	public List<AlunoJO> read(){
		String sql = "SELECT * FROM alunos";
		List<AlunoJO> alunos = new ArrayList<AlunoJO>();
		Connection conn = null;
		PreparedStatement p = null;
		ResultSet resultado = null;
		
		try {
			conn = ConexaoJO.CriandoConexao();
			p = (PreparedStatement) conn.prepareStatement(sql);//cast
			resultado = p.executeQuery();//mostra os dados da consulta sql
			while(resultado.next()) {
				AlunoJO ver_aluno = new AlunoJO();
				//recuperar RA
				ver_aluno.setRa(resultado.getInt("ra"));
				//recuperar o nome
				ver_aluno.setNome(resultado.getString("nome"));
				//recuperar idade
				ver_aluno.setIdade(resultado.getInt("idade"));
				//adicionar na lista
				alunos.add(ver_aluno);
				
			}
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "ERRO ao editar dados! \nERRO: " + e);
		}finally {
			try {
				if(p != null);
				p.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return alunos;
	}//fim READ
	//*****************************************************************
	
	//metodo D - Delete
	
	public boolean delete(int ra) {
		String sql = "DELETE FROM alunos WHERE ra = ?";
		Connection conn = null;
		PreparedStatement p = null;
		try {
			conn = ConexaoJO.CriandoConexao();
			p = (PreparedStatement) conn.prepareStatement(sql);//cast
			p.setInt(1, ra);
			p.execute();
			JOptionPane.showMessageDialog(null, "Dados excluidos com sucesso!");
			//System.out.println("Dados excluidos com sucesso!");
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "ERRO ao Excluir dados! \nERRO: " + e);
		}finally {
			try {
				if(p != null);
				p.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}//fim DELETE
	
		
		

}



















