const { Vue } = require("vue-class-component");

let list = new Vue({
    el: '#cleaning_table',
    data() {
      return {
        results: [
            {
                nr: 1231, 
                minibar: 'Tak'
            },
            {
                nr: 531, 
                minibar: 'Tak'
            },
            {
                nr: 21, 
                minibar: 'Nie'
            },
            {
                nr: 37, 
                minibar: 'Tak'
            },
            {
                nr: 67, 
                minibar: 'Nie'
            },
            {
                nr: 701, 
                minibar: 'Tak'
            }
        ]
      };
    },
  })

export default{
    list
}