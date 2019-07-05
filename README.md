# rastreio_de_encomendas
Para testar o sistema, deverá executar o arquivo 'sql_sistema.sql' em seu Banco de dados.

OBS: Alterar USER e PASSWORD na factory(ProgramacaoWeb/src/br/com/rastreioencomendas/factory/ConnectionFactory.java) para seu usuário e senha do banco.

Ao executar o sistema, realizar login com as seguintes credenciais, email: 'teste@gmail.com' e senha: '123456', 
após o login será redirecionado para o CRUD de usuários.

Os arquivos do CRUD de usuários para serem avaliados estão localizados respectivamente em:
- ProgramacaoWeb/WebContent/Pages/administrador/usuarios.xhtml
- ProgramacaoWeb/WebContent/template/dialogs/usuario/dlgCadUsuario.xhtml
- ProgramacaoWeb/WebContent/template/dialogs/usuario/dlgDelUsuario.xhtml
- ProgramacaoWeb/WebContent/template/dialogs/usuario/dlgEditUsuario.xhtml
- ProgramacaoWeb/src/br/com/rastreioencomendas/controller/UsuarioController.java
- ProgramacaoWeb/src/br/com/rastreioencomendas/dao/UsuarioDAO.java
