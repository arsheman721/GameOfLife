# GameOfLife
 John Conway's Game of Life AI using a Swing GUI
 I created this for my of my Level 1 Computer Science Java Programming module assignment, and since it is now over I decided that publishing it would be the best of things.
 
 If you are not familiar with the Game of Life, it is a form of Artificial Life simulation (Cellular Automata) that uses a the basic principle of a grid with two states: alive and dead.
 
 From there each iteration each cell is checked and the new state for the next iteration is set based upon:
<ul>
<li>A cell can go from dead to alive (be born) if it has 3 alive cells adjacent to it.</li>
<li>A cell will remain alive if it has 2 or 3 alive cells adjacent to it.</li>
<li>In all other cases a cell will die, from either lonliness (if it has less than 2 alive adjacent cells), or suffocation (if it has more than 3 alive adjacent cells)
</ul>

Reference/Information:
<ul>
<li>www.conwaylife.com/</li>
<li>https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life</li>
</ul>
