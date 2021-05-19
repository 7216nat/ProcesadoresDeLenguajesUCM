package tiny1.analizadorlexico;
import tiny1.errors.GestionErrores;
%%
%cup
%public
%line
%column
%class AnalizadorLexico
%type  UnidadLexica
%unicode

%{
  private AlexOperations ops;
  private GestionErrores errores;
  private int col;
  public String lexema() {return yytext();}
  public int fila() {return yyline+1;}
  public int col()  {return col;}
  public void incCol() {col += lexema().length();}
  public void fijaGestionErrores(GestionErrores errores) {
   this.errores = errores;
  }
%}

%eofval{
  return ops.token(ClaseLexica.EOF);
%eofval}

%init{
  ops = new AlexOperations(this);
  col = 1;
%init}

letra  = ([A-Z]|[a-z])
digitoPositivo = [1-9]
digito = ({digitoPositivo}|0)
parteEntera = {digitoPositivo}{digito}*
parteDecimal = {digito}* {digitoPositivo}
separador = [ \t\r\b]
saltoLinea = \n
comentario = #[^\n]* 
int = int
real = real
bool = bool
true = true
false = false
and = and
or = or
not = not
string = string
null = null
proc = proc
if = if
then = then
else = else
endif = endif
while = while
do = do
type = type
endwhile = endwhile
call = call
record = record
arr = array
of = of
ptr = pointer
new = new
del = delete
read = read
write = write
nl = nl
var = var
identificador = {letra}({letra}|{digito}|\_)*
numeroEntero = [\+,\-]?({parteEntera}|0)
numeroReal = [\+,\-]?({parteEntera}|0)(\.({parteDecimal}|0) | ((e|E){numeroEntero}) | \.({parteDecimal}|0)((e|E){numeroEntero}))
cadena = \"[^\b\r\n\"]*\"
operadorSuma = \+
operadorResta = \-
operadorMultiplicacion = \*
operadorDivision = \/
igual = \=
modulo = \%
gt = \>
ge = \>\=
lt = \<
le = \<\=
eq = \=\=
ne = \!\=
parentesisApertura = \(
parentesisCierre = \)
llaveApertura = \{
llaveCierre = \}
corcheteApertura = \[
corcheteCierre = \]
coma  = \,
punto = \.
puntocoma = \;
flecha = \-\>
ampersan = \&
sepseccion = \&\&
%%
{separador}               {incCol();}
{saltoLinea}              {col=1;} 
{comentario}              {incCol();}
{int}                     {return ops.token(ClaseLexica.INT);}
{real}                    {return ops.token(ClaseLexica.REAL);}
{bool}                    {return ops.token(ClaseLexica.BOOL);}  
{true}                    {return ops.token(ClaseLexica.TRUE);}
{false}                   {return ops.token(ClaseLexica.FALSE);}
{and}                     {return ops.token(ClaseLexica.AND);}
{or}                      {return ops.token(ClaseLexica.OR);}
{not}                     {return ops.token(ClaseLexica.NOT);}
{string}                  {return ops.token(ClaseLexica.STRING);}
{null}                    {return ops.token(ClaseLexica.NULL);}
{proc}                    {return ops.token(ClaseLexica.PROC);}
{if}                      {return ops.token(ClaseLexica.IF);}
{then}                    {return ops.token(ClaseLexica.THEN);}
{else}                    {return ops.token(ClaseLexica.ELSE);}
{endif}                   {return ops.token(ClaseLexica.ENDIF);}
{while}                   {return ops.token(ClaseLexica.WHILE);}
{do}                      {return ops.token(ClaseLexica.DO);}
{endwhile}                {return ops.token(ClaseLexica.ENDWHILE);}
{call}                    {return ops.token(ClaseLexica.CALL);}
{type}                    {return ops.token(ClaseLexica.TYPE);}
{record}                  {return ops.token(ClaseLexica.RECORD);}
{arr}                     {return ops.token(ClaseLexica.ARR);}
{of}                      {return ops.token(ClaseLexica.OF);}
{ptr}                     {return ops.token(ClaseLexica.PTR);}
{new}                     {return ops.token(ClaseLexica.NEW);}
{del}                     {return ops.token(ClaseLexica.DEL);}
{read}                    {return ops.token(ClaseLexica.READ);}
{write}                   {return ops.token(ClaseLexica.WRITE);}
{nl}                      {return ops.token(ClaseLexica.NL);}
{var}                     {return ops.token(ClaseLexica.VAR);}
{identificador}           {return ops.token(ClaseLexica.IDEN);}
{numeroEntero}            {return ops.token(ClaseLexica.ENT);}
{numeroReal}              {return ops.token(ClaseLexica.LREAL);}
{cadena}                  {return ops.token(ClaseLexica.CADENA);}
{igual}                   {return ops.token(ClaseLexica.IGUAL);}
{operadorSuma}            {return ops.token(ClaseLexica.MAS);}
{operadorResta}           {return ops.token(ClaseLexica.MENOS);}
{operadorMultiplicacion}  {return ops.token(ClaseLexica.POR);}
{operadorDivision}        {return ops.token(ClaseLexica.DIV);}
{modulo}                  {return ops.token(ClaseLexica.MOD);}
{gt}                      {return ops.token(ClaseLexica.GT);}
{ge}                      {return ops.token(ClaseLexica.GE);}
{lt}                      {return ops.token(ClaseLexica.LT);}
{le}                      {return ops.token(ClaseLexica.LE);}
{eq}                      {return ops.token(ClaseLexica.EQ);}
{ne}                      {return ops.token(ClaseLexica.NE);}
{parentesisApertura}      {return ops.token(ClaseLexica.PAP);}
{parentesisCierre}        {return ops.token(ClaseLexica.PCIE);}
{llaveApertura}           {return ops.token(ClaseLexica.LLAP);}
{llaveCierre}             {return ops.token(ClaseLexica.LLCIE);}
{corcheteApertura}        {return ops.token(ClaseLexica.CAP);}
{corcheteCierre}          {return ops.token(ClaseLexica.CCIE);}
{coma}                    {return ops.token(ClaseLexica.COMA);}
{punto}                   {return ops.token(ClaseLexica.PUNTO);}
{puntocoma}               {return ops.token(ClaseLexica.PUNTOCOMA);}
{flecha}                  {return ops.token(ClaseLexica.FLECHA);}
{ampersan}                {return ops.token(ClaseLexica.AMP);}
{sepseccion}              {return ops.token(ClaseLexica.SEPSECCION);}
[^]                       {errores.errorLexico(fila(),lexema());} 