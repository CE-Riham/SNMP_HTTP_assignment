function extractData(row) {
  let arr = row.split(" ");
  let result = "";

  for (let i = 1; i < arr.length; i++) {
    result += arr[i] + " ";
  }
  if (arr[0] === "STRING:") result = result.substring(1, result.length - 2);
  return result;
}

function callPhpFunction() {
  fetch("../php/getters.php?function=getSystemGroup")
    .then((response) => response.text())
    .then((data) => {
      console.log(data);
      const systemGroupTitles = [
        "sysDescr",
        "sysObjectID",
        "sysUpTime",
        "sysContact",
        "sysName",
        "sysLocation",
      ];

      let sysObject = JSON.parse(data);

      for (var i = 0; i < systemGroupTitles.length; i++) {
        let value = extractData(sysObject[`${i}`]);
        console.log(value);
        let tableRow = document.createElement("tr");
        let cell1 = document.createElement("td");
        let cell2 = document.createElement("td");
        cell1.innerText = systemGroupTitles[i];
        cell2.innerText = value;
        tableRow.appendChild(cell1);
        tableRow.appendChild(cell2);
        document.getElementById("systemGroupTable").appendChild(tableRow);
      }
    })
    .catch((error) => console.error("Request failed:", error));
}

callPhpFunction();
