package sinaisvitaispack;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/sinaisvitais")
@RestController 
public class SinaisVitaisController {
	
	
//	Mapeamento de metodo HTTP Post, /tarefa, onde recebe no corpo da requisicao, as informacoes para adicionar uma nova tarefa na lista, em formato JSON
	@PostMapping
	public SinaisVitais insertSinaisVitais(@Valid @RequestBody SinaisVitais sinaisvitais) { 
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		try {	
			stmt = con.prepareStatement("insert into "
					+ "sinaisvitais (id, pulso, sistolica, diastolica, freqRespiratoria, temperatura, observacao) "
					+ "values (?, ?, ?, ?, ?, ?, ?)");
			stmt.setInt(1, sinaisvitais.identificador);
			stmt.setInt(2, sinaisvitais.pulso);
			stmt.setInt(3, sinaisvitais.sistolica);
			stmt.setInt(4, sinaisvitais.diastolica);
			stmt.setInt(5, sinaisvitais.freqRespiratoria);
			stmt.setFloat(6, sinaisvitais.temperatura);
			stmt.setString(7, sinaisvitais.observacao);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			ConnectionFactory.FecharConexao(con);
		}
		 
		 return sinaisvitais; 
	}
	
	
// 	Mapeamento de metodo GET, /tarefa/ID, onde devolve a tarefa com o ID utilizado na requisicao.
//	Utiliza ResponseEntity para retornar os codigos HTTP, caso não encontre (método findById é Long, caso não encontre, retorna um NULL), retornar um 404
//	Se encontrar, retorna a tarefa e o código 200.
	@GetMapping("/{id}")
	public ResponseEntity<SinaisVitais> select(@PathVariable int id) {

		SinaisVitais sinaisvitais = new SinaisVitais();
		
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = con.prepareStatement("SELECT * FROM sinaisvitais where id=?");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				
				sinaisvitais.setIdentificador(rs.getInt("id"));
				sinaisvitais.setPulso(rs.getInt("pulso"));
				sinaisvitais.setSistolica(rs.getInt("sistolica"));
				sinaisvitais.setDiastolica(rs.getInt("diastolica"));
				sinaisvitais.setFreqRespiratoria(rs.getInt("freqRespiratoria"));
				sinaisvitais.setTemperatura(rs.getFloat("temperatura"));
				sinaisvitais.setObservacao(rs.getString("observacao"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.FecharConexao(con);
		}
		
		return ResponseEntity.ok(sinaisvitais);
		
	}

//	Mapeamento do metodo PUT, /tarefa/ID, onde é alterada a tarefa com o ID utilizado na requisicao.
//	Utiliza os dados em JSON no corpo da requisicao como o PostMapping, e realiza busca e retorno como o metodo GET por ID.
// 	Para a atualizacao da tarefa, utiliza um bean para realizar a copia da tarefa inteira	
	@PutMapping("/{id}")
	public ResponseEntity<SinaisVitais> update(@PathVariable /*Long*/int id, @Valid @RequestBody SinaisVitais sinaisvitais) {
		
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		try {	
			stmt = con.prepareStatement("update sinaisvitais "
					+ "set pulso =?, sistolica =?, diastolica =?, freqRespiratoria=?, "
					+ "temperatura=?, observacao=? where id =?");
			stmt.setInt(1, sinaisvitais.pulso);
			stmt.setInt(2, sinaisvitais.sistolica);
			stmt.setInt(3, sinaisvitais.diastolica);
			stmt.setInt(4, sinaisvitais.freqRespiratoria);
			stmt.setFloat(5, sinaisvitais.temperatura);
			stmt.setString(6, sinaisvitais.observacao);
			stmt.setInt(7, id);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			ConnectionFactory.FecharConexao(con);
		}
		
		return ResponseEntity.ok(sinaisvitais);
		
	}
	
//	Mapeamento do metodo DELETE, /tarefa/ID, onde é removida a tarefa com o ID utilizado na requisicao.
//	Trabalha da mesma forma que o GET por ID, porém realiza a delecao da tarefa encontrada.	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable int id) {

		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		try {	
			stmt = con.prepareStatement("delete from sinaisvitais where id =?");
			stmt.setInt(1, id);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			ConnectionFactory.FecharConexao(con);
		}
		
		return ResponseEntity.noContent().build();
	}
	 
}