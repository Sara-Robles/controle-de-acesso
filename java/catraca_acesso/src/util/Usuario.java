package util;

public class Usuario {

    // Atributos
    private String cpf, nome, senha;

    // Construtores
    public Usuario() {
    }

    public Usuario(String cpf, String nome, String senha) {
    	this.cpf = cpf;
    	this.nome = nome;
        this.senha = senha;
    }

    // Getters e Setters

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

   
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
 
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    @Override
    public String toString() {
        return "Usuario{" +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                '}';
    }
}
