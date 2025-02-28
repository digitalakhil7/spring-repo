**Note:** fetch() and async functions return a Promise <br/>
```js
let userPromise = fetch('https://api.github.com/users/digitalakhil7');

console.log(userPromise);

async function namePromise() {
    return "akhil";
}

console.log(namePromise());
```
Producer creates a Promise using Promise class <br/>
Consumer consumes a Promise using then() or async-await
## 1. Callbacks
calling a function inside another function
```js
function fetchData(callback){
    callback("hello");
}

fetchData(data=>console.log(data));
```
## 2. Promises
```js
let p1 = new Promise((resolve, reject)=>{
    setTimeout(() => {
        resolve("p1 success");
    }, 2000);
});

p1.then(posRes => console.log("posRes: ",posRes));
```
## 3. Async / Await
```js
async function fetchUsers(){
    let p2 = new Promise((resolve, reject)=>{
        setTimeout(() => {
            resolve("p2 success")
        }, 5000);
    });

    let response = await p2;
    console.log(response);
}

fetchUsers();
```
