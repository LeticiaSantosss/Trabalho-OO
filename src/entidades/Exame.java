package entidades;
import java.time.LocalDate;

public class Exame {
     private String tipo;
   private LocalDate dataRealização;
  private  String resultado;
  private  double custo;
   
  Exame(String tipo,LocalDate dataRealização , String resultado, double custo){
	  this.setTipo(tipo);
	  this.setDataRealização(dataRealização);
	  this.setResultado(resultado);
	  this.setCusto(custo);
  }

public String getTipo() {
	return tipo;
}

public void setTipo(String tipo) {
	this.tipo = tipo;
}

public LocalDate getDataRealização() {
	return dataRealização;
}

public void setDataRealização(LocalDate dataRealização) {
	this.dataRealização = dataRealização;
}

public String getResultado() {
	return resultado;
}

public void setResultado(String resultado) {
	this.resultado = resultado;
}

public double getCusto() {
	return custo;
}

public void setCusto(double custo) {
	this.custo = custo;
}


  
}
