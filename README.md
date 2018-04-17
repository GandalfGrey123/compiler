

<h1>Java Like Compiler </h1>

Full functional Java like compiler

Entirely impemeneted in Java, Reads source code into interpretable objects

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


Constrainer 

Parser 

Ast and Code Gen


<br>

Degugger and Execution Classes
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
 
debugger/debug class and debug bytecode


debugger class implements a debugging mode for source code execution 
