package sinaisvitaispack;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SinaisVitais {
		
//		ID é o identificador da tarefa, que necessita ser única para não ter problemas ao adicionar na base em memória (é utilizado H2)
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;	
		
		int identificador;
		int pulso;
		int sistolica;
		int diastolica;
		int freqRespiratoria;
		float temperatura;
		String observacao;
		
	    public Long getId() {
	        return id;
	    }
	 
	    public void setId(Long id) {
	        this.id = id;
	    }
	    
	    public int getIdentificador() {
	        return identificador;
	    }
	 
	    public void setIdentificador(int identificador) {
	        this.identificador = identificador;
	    }
	    
	    public int getPulso() {
	    	return pulso;
	    }

	    public void setPulso(int pulso) {
	    		this.pulso = pulso;
	    }
	    
	    public int getSistolica() {
	    	return sistolica;
	    }

	    public void setSistolica(int sistolica) {
	    		this.sistolica = sistolica;
	    }
	    
	    public int getDiastolica() {
	    	return diastolica;
	    }

	    public void setDiastolica(int diastolica) {
	    		this.diastolica = diastolica;
	    }
	    
	    public int getFreqRespiratoria() {
	    	return freqRespiratoria;
	    }

	    public void setFreqRespiratoria(int freqRespiratoria) {
	    		this.freqRespiratoria = freqRespiratoria;
	    }
	    
	    public float getTemperatura() {
	        return temperatura;
	    }
	 
	    public void setTemperatura(float temperatura) {
	        this.temperatura = temperatura;
	    }
	    
	    public String getObservacao() {
	        return observacao;
	    }
	     
	    public void setObservacao(String observacao) {
	        this.observacao = observacao;
	    }
}