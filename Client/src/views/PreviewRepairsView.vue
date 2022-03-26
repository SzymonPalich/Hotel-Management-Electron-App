<template>
<div class="relative w-full flex flex-col h-screen overflow-y-hidden">
    <div class="w-full h-screen overflow-x-hidden flex flex-col">
        <div class="w-full flex-grow p-6">
            <div class="w-full mt-6">
                <div class="overflow-auto rounded-xl ml-12 mr-12">
                    <div class="w-1/2 h-full bg-white border-2 border-solid border-black rounded-xl justify-center">
                        <h1 class="text-xl">Podgląd</h1>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</template>

<script>
    export default {
        name: 'preview_repair',
        data () {
            return {
                id: 0,
                issue: "",
                room_nr: ""
            }   
        },
        created() {
            this.id = this.$route.params.id;
            this.issue = this.$route.params.issue;
            this.room_nr = this.$route.params.room_nr;
        }
    }
</script>