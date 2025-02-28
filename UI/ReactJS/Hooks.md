## Project setup using Vite
```js
npm create vite@latest my-react-app --template react
```
## useState() - Count App
```js
import React, { useState } from 'react'

function App() {

  const [count, setCount] = useState(0);

  const test = useState();
  console.log(test);

  function increment() {
    setCount(count + 1);
  }

  function decrement() {
    setCount(count - 1);
  }
  
  return (
    <div>
      <h1>{count}</h1>
      <button onClick={increment}>Increment</button>
      <button onClick={decrement}>Decrement</button>
    </div>
  )
}

export default App
```
## useState() - Time App
```js
import React, { useState } from 'react'

function App() {

  const [time, setTime] = useState(new Date().toLocaleTimeString());

  //1. setTime on button click
  function getNewTime(){
    setTime(new Date().toLocaleTimeString());
  }

  //2. setTime for every 1 sec
  // setInterval(() => {
  //   setTime(new Date().toLocaleTimeString());
  // }, 1000);

  return (
    <div>
      <h1>{time}</h1>
      <button onClick={getNewTime}>GET TIME</button>
    </div>
  )
}

export default App
```
