<template>
    <div>
        <input type="text" v-model="newTodoItem">
        <button v-on:click="addTodo">add</button>
    </div>
</template>

<script>
export default {
    name: 'VueJsTodoInput',

    data() {
        return {
            newTodoItem: ""
        };
    },

    mounted() {
     
    },

    methods: {
           addTodo: function() {
            console.log(this.newTodoItem);
            // 저장하는 로직
            localStorage.setItem(this.newTodoItem, this.newTodoItem);
            this.newTodoItem = '';
        }
    },
};
</script>

<style>

</style>