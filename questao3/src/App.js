import './App.css';
import { useState } from 'react';

function App() {
  const [count, setCount] = useState(0);

  function ButtonIncrement({ count }) {
    function handleClick() {
      setCount(count + 1);
      console.log(count);
    }

    return (<button className='increment' onClick={handleClick}> Incremento </button>);
  }

  function ButtonReset({ count }) {
    function handleClick() {
      setCount(0);
      console.log(count);
    }

    return (<button className='reset' onClick={handleClick}> Reiniciar </button>);
  }

  let struct = (
    <div className="App">
      <div>
        <h1>Contador</h1>
      </div>
      <h2>{count}</h2>
      <ButtonIncrement count={count} /> <ButtonReset count={count} />
    </div>
  );

  return struct;
}

export default App;
