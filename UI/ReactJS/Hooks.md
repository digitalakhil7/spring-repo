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
## useReducer() - Counter App
const [state, dispatch] = useReducer(reducer, initialState); <br/>
useReducer is an alternative to useState() Hook <br/>
dispatch function will be sent as action to the reducer function
```js
import React, { useReducer } from "react"

function App() {
  const reducer = (state, action) => {
    switch (action.type) {
      case "inc":
        return state + 1;
      case "dec":
        return state - 1;
      default:
        return state;
    }
  }
  const[state, dispatch] = useReducer(reducer, 0);
  return(
    <div>
      <h1>Reducer: {state}</h1>
      <button onClick={()=>dispatch({type:"inc"})}>Increment</button>
      <button onClick={()=>dispatch({type:"dec"})}>Decrement</button>
    </div>
  )
}


export default App
```
