<template>
 <div class="w-full h-screen80 mt-12">
    <h1 class="text-4xl text-white pb-6 text-center">Naprawy</h1>
    <div class="w-full mt-6">
        <div class="overflow-auto rounded-xl mx-48">
            <table class="min-w-full">
                <thead class="bg-gray-800 text-white">
                    <tr>
                        <th class="w-1/3 text-left py-3 px-4 uppercase font-semibold text-sm">Usterka</th>
                        <th class="w-1/3 text-center py-3 px-4 uppercase font-semibold text-sm">Numer Pokoju</th>
                        <th class="w-1/3 text-center py-3 px-4">Akcja</th>
                    </tr>
                </thead>
                <tbody class="text-gray-700">
                    <tr class="bg-white">
                        <td class="w-1/3 text-left py-3 px-4">Telewizor</td>
                        <td class="w-1/3 text-center py-3 px-4">113</td>
                        <td class="w-1/3 text-center py-3 px-4">
                            <i class="material-icons" @click="przejdz(1,'Telewizor',113)">description</i>
                            <i class="material-icons">edit</i>
                            <i class="material-icons text-red-500" @click="alertDisplay">delete</i>
                        </td>
                    </tr>
                    <tr class="bg-gray-200">
                        <td class="w-1/3 text-left py-3 px-4">Cieknący kran</td>
                        <td class="w-1/3 text-center py-3 px-4">53</td>
                        <td class="w-1/3 text-center py-3 px-4">
                            <i class="material-icons" @click="przejdz(2,'Cieknący kran',53)">description</i>
                            <i class="material-icons">edit</i>
                            <i class="material-icons text-red-500" @click="alertDisplay">delete</i>
                        </td>
                    </tr>
                    <tr id="3" class="bg-white">
                        <td class="w-1/3 text-left py-3 px-4">Wybita szyba</td>
                        <td class="w-1/3 text-center py-3 px-4">22</td>
                        <td class="w-1/3 text-center py-3 px-4">
                            <i class="material-icons" @click="przejdz(3,'Wybita szyba',22)">description</i>
                            <i class="material-icons">edit</i>
                            <i class="material-icons text-red-500" @click="alertDisplay">delete</i>
                        </td>
                    </tr>
                    <tr class="bg-gray-200">
                        <td class="w-1/3 text-left py-3 px-4">Wyrwana klamka</td>
                        <td class="w-1/3 text-center py-3 px-4">9</td>
                        <td class="w-1/3 text-center py-3 px-4">
                            <i class="material-icons" @click="przejdz(4,'Wyrwana klamka',9)">description</i>
                            <i class="material-icons">edit</i>
                            <i class="material-icons text-red-500" @click="alertDisplay">delete</i>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
        <p class="pt-3 text-gray-600">
        </p>
    </div>
  </div>
</template>

<script lang="ts">
  export default {
    data() {
      return {}
    },
    methods: {
      alertDisplay() {
        this.$swal({
          title: 'Jesteś pewien?',
          type: 'warning',
          showCancelButton: true,
          confirmButtonText: 'Tak',
          confirmButtonColor: '#FF2D00',
          cancelButtonText: 'Nie',
          cancelButtonColor: '#187800',
          showLoaderOnConfirm: true
        }).then((result) => {
          if(result.value) {
            this.$swal('Usunięte', 'Pomyślnie usunąłeś rekord', 'success')
          }
        })
      },
      przejdz(arg1, arg2, arg3) {
        this.$router.push({name: 'preview_repair', params: { id: arg1, issue: arg2, room_nr: arg3 } });
      }
    }
  }
</script>