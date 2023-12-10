function extractData(row) {
  let arr = row.split(" ");
  return arr[1];
}

function callPhpFunction() {
  fetch("../php/getters.php?function=getUDPTable")
    .then((response) => response.text())
    .then((data) => {
      let updObject = JSON.parse(data);
      console.log(data);
      let numberOfRows = Object.keys(updObject).length / 2;
      for (var i = 0; i < numberOfRows; i++) {
        console.log(i);
        let tableRow = document.createElement("tr");

        let cell1 = document.createElement("td");
        cell1.innerText = i;

        let cell2 = document.createElement("td");
        let value = extractData(updObject[`${i}`]);
        console.log(value);
        cell2.innerText = value;

        let cell3 = document.createElement("td");
        value = extractData(updObject[`${i + numberOfRows}`]);
        console.log(value);
        cell3.innerText = value;

        tableRow.appendChild(cell1);
        tableRow.appendChild(cell2);
        tableRow.appendChild(cell3);
        document.getElementById("systemGroupTable").appendChild(tableRow);
      }
    })
    .catch((error) => console.error("Request failed:", error));
}

callPhpFunction();
