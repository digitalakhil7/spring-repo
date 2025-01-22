## useState()
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
