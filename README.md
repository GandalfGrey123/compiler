

<h1>Java Like Compiler </h1>

Full functional Java like compiler

Entirely impemeneted in Java, Reads source code into interpretable objects

Compiles psuedo basic source code "x code" .x files

Utilizes standard library

<br>

Compiler Classes

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

Execution Classes
 
vm/intepreter and byte code class
 
debugger/debug class and debug bytecode


debugger class implements a debugging mode for source code execution 
