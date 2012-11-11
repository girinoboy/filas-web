package br.com.models;

import java.io.Serializable;

public class Opcao
  implements Serializable
{
  private static final long serialVersionUID = 3598147865115425993L;
  private String campo;

  public Opcao()
  {
    this.campo = "";
  }

  public Opcao(String campo) {
    this.campo = campo;
  }

  public String getCampo() {
    return this.campo;
  }

  public void setCampo(String campo) {
    this.campo = campo;
  }

  public String toString()
  {
    return this.campo;
  }
}

/* Location:           C:\Users\Marcleônio\Desktop\dinamico\WEB-INF\classes\
 * Qualified Name:     modelos.Opcao
 * JD-Core Version:    0.6.2
 */