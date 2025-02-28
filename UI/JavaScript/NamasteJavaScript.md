## Callbacks - calling a function inside another function
In the example function b is a callback function
```js
setTimeout(() => {
    console.log('timers');
}, 5000);

function a(b){
    console.log('a');
    b();
}

a(function b(){
    console.log('b');
});
```
## Clousures and Event Listeners
```js
function attachEventListeners(){
    let count = 0;
    document.getElementById("clickMe").addEventListener("click", ()=>{
        console.log('button clicked: ',++count);
    });
}

attachEventListeners();
```
