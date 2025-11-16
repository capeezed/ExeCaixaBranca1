import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    // Método responsável por criar a conexão com o banco de dados MySQL
    public Connection conectarBD() {
        Connection conn = null;
        try {
            // Carrega o driver do MySQL (atenção: nome correto do driver é 'com.mysql.cj.jdbc.Driver')
            Class.forName("com.mysql.Driver.Manager").newInstance(); // Corrija se necessário para evitar erros
            // Define a URL de conexão com usuário e senha
            String url = "jdbc:mysql://127.0.0.1/test?user=lopes&password=123";
            // Tenta conectar ao banco de dados usando a URL indicada
            conn = DriverManager.getConnection(url);
        } catch (Exception e) {
            // Em caso de erro, apenas retorna null (poderia ser interessante logar o erro)
        }
        return conn; // Retorna a conexão (ou null, caso falhe)
    }

    // Armazena o nome do usuário logado, caso a autenticação seja bem-sucedida.
    public String nome = "";
    // Indica se o usuário foi autenticado com sucesso.
    public boolean result = false;

    // Realiza a verificação das credenciais do usuário (login e senha)
    public boolean verificarUsuario(String login, String senha) {
        String sql = "";
        // Obtém a conexão com o banco
        Connection conn = conectarBD();
        // Monta a consulta SQL para buscar o usuário com os dados fornecidos
        sql += "select nome from usuarios ";
        sql += "where login = '" + login + "'";
        sql += " and senha = '" + senha + ";";
        try {
            // Cria um "Statement" (objeto que executa comandos SQL)
            Statement st = conn.createStatement();
            // Executa a consulta e recebe o resultado
            ResultSet rs = st.executeQuery(sql);
            // Verifica se encontrou um registro com essas credenciais
            if (rs.next()) {
                result = true; // Usuário autenticado
                nome = rs.getString("nome"); // Armazena o nome retornado
            }
        } catch (Exception e) {
            // Captura qualquer problema na execução (poderia logar a exceção)
        }
        return result; // Retorna se houve sucesso ou não
    }
    
}
