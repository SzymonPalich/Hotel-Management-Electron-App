<template>
    <div class="relative w-full flex flex-col h-screen overflow-y-hidden">
        <div class="w-full h-screen overflow-x-hidden flex flex-col">
            <div class="w-full flex-grow p-6">
                <h1 class="text-4xl text-white pb-6 text-center">Naprawy</h1>

                <div class="w-full mt-6">
                    <div class="overflow-auto rounded-xl w-3/4">
                        <table class="min-w-full ">
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
                                        <i class="material-icons">description</i>
                                        <i class="material-icons">edit</i>
                                        <i class="material-icons text-red-500">delete</i>
                                    </td>
                                </tr>
                                <tr class="bg-gray-200">
                                    <td class="w-1/3 text-left py-3 px-4">Cieknący kran</td>
                                    <td class="w-1/3 text-center py-3 px-4">53</td>
                                    <td class="w-1/3 text-center py-3 px-4">
                                        <i class="material-icons">description</i>
                                        <i class="material-icons">edit</i>
                                        <i class="material-icons text-red-500">delete</i>
                                    </td>
                                </tr>
                                <tr class="bg-white">
                                    <td class="w-1/3 text-left py-3 px-4">Wybita szyba</td>
                                    <td class="w-1/3 text-center py-3 px-4">22</td>
                                    <td class="w-1/3 text-center py-3 px-4">
                                        <i class="material-icons">description</i>
                                        <i class="material-icons">edit</i>
                                        <i class="material-icons text-red-500">delete</i>
                                    </td>
                                </tr>
                                <tr class="bg-gray-200">
                                    <td class="w-1/3 text-left py-3 px-4">Wyrwana klamka</td>
                                    <td class="w-1/3 text-center py-3 px-4">9</td>
                                    <td class="w-1/3 text-center py-3 px-4">
                                        <i class="material-icons">description</i>
                                        <i class="material-icons">edit</i>
                                        <i class="material-icons text-red-500">delete</i>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <p class="pt-3 text-gray-600">
                    </p>
                </div>
            </div>
        </div>
    </div>
</template>

<script lang="ts">
import { Vue } from "vue-class-component";

export default class RepairsView extends Vue { }
</script>