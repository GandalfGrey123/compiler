

<h1>Java Like Compiler </h1>

Full functional Java like compiler

Entirely impemeneted in Java, Reads block style source code into interpretable objects

Compiles psuedo basic source code "x code" .x files

Utilizes standard library

<br>

Compilation Classes
------

`SourceReader`
 <ul>
  <li>reads target source file </li>
 <li>read in line number and each line for debugging</li>
 
 </ul>

`Lexer` 
 <ul> 
 <li>tokenizes each char read in from SourceReader</li> 
   <li>dynamically tokenize source code symbols into token objects</li>
</ul>

`Parser`
 <ul> 
 <li>Analyze token stream</li> 
  <li>check source code syntax and semantics </li> 
   <li>Create a abstract syntax tree</li> 
</ul>



`Vistor`
 <ul> 
 <li>vistor walks syntax node style tree </li> 
</ul>

`Constrainer` `Ast Classes` and `Code Gen`


<br>

Execution Classes
------
 
`Intepreter`
 <ul>
  <li> Creates Program object and loads byte codes into Program object</li>
   <li> </li>
 </ul>

`Virtual Machine`
 <ul>
  <li> Execute ByteCode objects loaded into the interpreters Program object</li>
 </ul>


`ByteCode`
 <ul>
 <li>Abstract class used to implement different byte codes objects</li>
 </ul>
 
 
Degugg Mode
------
 
Can run debugger mode given command line argument upon execution

compiler/Debugger contains Debug bytecode

saves source line numbers


