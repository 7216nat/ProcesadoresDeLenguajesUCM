package tiny1.analizadorlexico;

%%
%cup
%public
%line
%column
%class AnalizadorLexicoTiny
%type  UnidadLexica
%unicode

%{
  private AlexOperations ops;
  public String lexema() {return yytext();}
  public int fila() {return yyline+1;}
  public int columna() {return yycolumn+1;}
%}

%eofval{
  return ops.unidadEof();
%eofval}

%init{
  ops = new AlexOperations(this);
%init}

letra  = ([A-Z]|[a-z])
digitoPositivo = [1-9]
digito = ({digitoPositivo}|0)
parteEntera = {digitoPositivo}{digito}*
parteDecimal = {digito}* {digitoPositivo}
separador = [ \t\r\b\n]
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
{separador}               {}
{comentario}              {}
{int}                     {return ops.unidadInt();}
{real}                    {return ops.unidadReal();}
{bool}                    {return ops.unidadBool();}                    
{true}                    {return ops.unidadTrue();}
{false}                   {return ops.unidadFalse();}
{and}                     {return ops.unidadAnd();}
{or}                      {return ops.unidadOr();}
{not}                     {return ops.unidadNot();}
{string}                  {return ops.unidadString();}
{null}                    {return ops.unidadNull();}
{proc}                    {return ops.unidadProc();}
{if}                      {return ops.unidadIf();}
{then}                    {return ops.unidadThen();}
{else}                    {return ops.unidadElse();}
{endif}                   {return ops.unidadEndif();}
{while}                   {return ops.unidadWhile();}
{do}                      {return ops.unidadDo();}
{endwhile}                {return ops.unidadEndwhile();}
{call}                    {return ops.unidadCall();}
{type}                    {return ops.unidadType();}
{record}                  {return ops.unidadRecord();}
{arr}                     {return ops.unidadArr();}
{of}                      {return ops.unidadOf();}
{ptr}                     {return ops.unidadPtr();}
{new}                     {return ops.unidadNew();}
{del}                     {return ops.unidadDel();}
{read}                    {return ops.unidadRead();}
{write}                   {return ops.unidadWrite();}
{nl}                      {return ops.unidadNl();}
{var}                     {return ops.unidadVar();}
{identificador}           {return ops.unidadId();}
{numeroEntero}            {return ops.unidadEnt();}
{numeroReal}              {return ops.unidadLReal();}
{cadena}                  {return ops.unidadCadena();}
{igual}                   {return ops.unidadIgual();} 
{operadorSuma}            {return ops.unidadSuma();}
{operadorResta}           {return ops.unidadResta();}
{operadorMultiplicacion}  {return ops.unidadMul();}
{operadorDivision}        {return ops.unidadDiv();}
{modulo}                  {return ops.unidadMod();}
{gt}                      {return ops.unidadGT();}
{ge}                      {return ops.unidadGE();}
{lt}                      {return ops.unidadLT();}
{le}                      {return ops.unidadLE();}
{eq}                      {return ops.unidadEQ();}
{ne}                      {return ops.unidadNE();}
{parentesisApertura}      {return ops.unidadPAp();}
{parentesisCierre}        {return ops.unidadPCie();}
{llaveApertura}           {return ops.unidadLlAp();}
{llaveCierre}             {return ops.unidadLlCie();}
{corcheteApertura}        {return ops.unidadCAp();}
{corcheteCierre}          {return ops.unidadCCie();}
{coma}                    {return ops.unidadComa();}
{punto}                   {return ops.unidadPunto();}
{puntocoma}               {return ops.unidadPuntocoma();}
{flecha}                  {return ops.unidadFlecha();}
{ampersan}                {return ops.unidadAmp();}
{sepseccion}              {return ops.unidadSepseccion();}
[^]                       {ops.error();}  