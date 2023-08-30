# 뷰 기초2





```vue
<template>
  <div class="container">
    <h2>To-Do List</h2>
    <form  
      @submit.prevent="onSubmit"
      class="d-flex"
    >
      <div class="flex-grow-1 mr-2">
        <input
          class="form-control"
          type="text" 
          v-model="todo"
          placeholder="Type new to-do"
        >
      </div>
      <div>
        <button 
          class="btn btn-primary"
          type="submit"
        >
          Add
        </button>
      </div>
    </form>
    <div class="card mt-2">
      <div class="card-body p-2">
        {{ todos[0].subject }}
      </div>
    </div>
    <div class="card mt-2">
      <div class="card-body p-2">
        {{ todos[1].subject }}
      </div>
    </div>
  </div>
</template>

<script>
import { ref } from 'vue';

export default {
  setup() {
    
    const todo = ref('');

    const todos = ref([
      {id: 1, subject: '휴대폰 사기'},
      {id: 2, subject: '장보기'},
    ]);

    const onSubmit = () => {
      todos.value.push({
        id: Date.now(),
        subject: todo.value
      });
    };

    return {
      todo,
      todos,
      onSubmit,
    };
  }
}
</script>

<style>
  .name {
    color: red;
  }
</style>
```



### 12 v-for

```vue
<template>
  <div class="container">
    <h2>To-Do List</h2>
    <form  
      @submit.prevent="onSubmit"
      class="d-flex"
    >
      <div class="flex-grow-1 mr-2">
        <input
          class="form-control"
          type="text" 
          v-model="todo"
          placeholder="Type new to-do"
        >
      </div>
      <div>
        <button 
          class="btn btn-primary"
          type="submit"
        >
          Add
        </button>
      </div>
    </form>
    <div v-for="todo in todos" :key="tdoo.id" class="card mt-2">
      <div class="card-body p-2">
        {{ todo.subject }}
      </div>
    </div>
  </div>
</template>

<script>
import { ref } from 'vue';

export default {
  setup() {
    
    const todo = ref('');

    const todos = ref([
      {id: 1, subject: '휴대폰 사기'},
      {id: 2, subject: '장보기'},
    ]);

    const onSubmit = () => {
      todos.value.push({
        id: Date.now(),
        subject: todo.value
      });
    };

    return {
      todo,
      todos,
      onSubmit,
    };
  }
}
</script>

<style>
  .name {
    color: red;
  }
</style>
```



### 13 v-show vs v-if

- 토글 참 거짓 변경하기

```vue
<template>
  <div v-show="toggle">true</div>
  <div v-show="!toggle">false</div>
  <button @click="onToggle">Toglle</button>
  <div class="container">
    <h2>To-Do List</h2>
    <form  
      @submit.prevent="onSubmit"
      class="d-flex"
    >
      <div class="flex-grow-1 mr-2">
        <input
          class="form-control"
          type="text" 
          v-model="todo"
          placeholder="Type new to-do"
        >
      </div>
      <div>
        <button 
          class="btn btn-primary"
          type="submit"
        >
          Add
        </button>
      </div>
    </form>
    <div v-for="todo in todos" :key="todo.id" class="card mt-2">
      <div class="card-body p-2">
        {{ todo.subject }}
      </div>
    </div>
  </div>
</template>

<script>
import { ref } from 'vue';

export default {
  setup() {
    const toggle = ref(false);
    const onToggle = () => {
      toggle.value = !toggle.value
      };
    
      const todo = ref('');
    const todos = ref([
      {id: 1, subject: '휴대폰 사기'},
      {id: 2, subject: '장보기'},
    ]);

    const onSubmit = () => {
      todos.value.push({
        id: Date.now(),
        subject: todo.value
      });
    };

    return {
      todo,
      todos,
      onSubmit,
      toggle,
      onToggle,
    };
  }
}
</script>

<style>
  .name {
    color: red;
  }
</style>
```



### 빈칸 입력하는 경우 처리하기

- 자주 사용되는 것은 `v-if` 보다 `v-show` 를 사용하는 것이 바람직합니다.

```vue
<template>
  <div class="container">
    <h2>To-Do List</h2>
    <form @submit.prevent="onSubmit">
      <div class="d-flex">
        <div class="flex-grow-1 mr-2">
          <input
            class="form-control"
            type="text" 
            v-model="todo"
            placeholder="Type new to-do"
          >
        </div>
        <div>
          <button 
            class="btn btn-primary"
            type="submit"
          >
            Add
          </button>
        </div>
      </div>
      <div v-show="hasError" style="color: red">
        This field cannot be empty
      </div>
    </form>
    <div 
      v-for="todo in todos"
      :key="todo.id"
      class="card mt-2"
    >
      <div class="card-body p-2">
        {{ todo.subject }}
      </div>
    </div>
  </div>
</template>

<script>
import { ref } from 'vue';

export default {
  setup() {
    const todo = ref('');
    const todos = ref([
      {id: 1, subject: '휴대폰 사기'},
      {id: 2, subject: '장보기'},
    ]);
    const hasError = ref(false);

    const onSubmit = () => {
      if (todo.value === '') {
        hasError.value = true;
      } else {
        todos.value.push({
          id: Date.now(),
          subject: todo.value
        });
        hasError.value = false;
      }
    };

    return {
      todo,
      todos,
      onSubmit,
      hasError,
    };
  }
}
</script>

<style>
  .name {
    color: red;
  }
</style>
```



### 14. 체크박스 바인딩과 체크하면 줄 삽입

```vue
<template>
  <div class="container">
    <h2>To-Do List</h2>

    <form @submit.prevent="onSubmit">
      <div class="d-flex">
        <div class="flex-grow-1 mr-2">
          <input
            class="form-control"
            type="text" 
            v-model="todo"
            placeholder="Type new to-do"
          >
        </div>
        <div>
          <button 
            class="btn btn-primary"
            type="submit"
          >
            Add
          </button>
        </div>
      </div>
      <div v-show="hasError" style="color: red">
        This field cannot be empty
      </div>
    </form>

    <div 
      v-for="todo in todos"
      :key="todo.id"
      class="card mt-2"
    >    
      <div class="card-body p-2">
        <div class="form-check">
          <input class="type-check-input" type="checkbox" v-model="todo.completed">
          <label class="form-check-label" :style="todo.completed ? todoStyle : {}">
          {{ todo.subject }}
        </label>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { ref } from 'vue';

export default {
  setup() {
    const todo = ref('');
    const todos = ref([]);
    const hasError = ref(false);
    const todoStyle = {
      textDecoration: 'line-through',
      color: 'gray'
    };

    const onSubmit = () => {
      if (todo.value === '') {
        hasError.value = true;
      } else {
        todos.value.push({
          id: Date.now(),
          subject: todo.value,
          completed: false,
        });
        hasError.value = false;
        todo.value = '';
      }
    };

    return {
      todo,
      todos,
      onSubmit,
      hasError,
      todoStyle,
    };
  }
}
</script>

<style>
  .name {
    color: red;
  }
</style>
```



### 16 object syntax

- CSS 스타일을 활용해서 밑 줄 출력을 클래스 바인딩하는 방법입니다.

```vue
<template>
  <div class="container">
    <h2>To-Do List</h2>

    <form @submit.prevent="onSubmit">
      <div class="d-flex">
        <div class="flex-grow-1 mr-2">
          <input
            class="form-control"
            type="text" 
            v-model="todo"
            placeholder="Type new to-do"
          >
        </div>
        <div>
          <button 
            class="btn btn-primary"
            type="submit"
          >
            Add
          </button>
        </div>
      </div>
      <div v-show="hasError" style="color: red">
        This field cannot be empty
      </div>
    </form>

    <div 
      v-for="todo in todos"
      :key="todo.id"
      class="card mt-2"
    >    
      <div class="card-body p-2">
        <div class="form-check">
          <input class="type-check-input" type="checkbox" v-model="todo.completed">
          <label class="form-check-label" :class="{todo: todo.completed}">
          {{ todo.subject }}
        </label>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { ref } from 'vue';

export default {
  setup() {
    const todo = ref('');
    const todos = ref([]);
    const hasError = ref(false);
    const todoStyle = {
      textDecoration: 'line-through',
      color: 'gray'
    };

    const onSubmit = () => {
      if (todo.value === '') {
        hasError.value = true;
      } else {
        todos.value.push({
          id: Date.now(),
          subject: todo.value,
          completed: false,
        });
        hasError.value = false;
        todo.value = '';
      }
    };

    return {
      todo,
      todos,
      onSubmit,
      hasError,
      todoStyle,
    };
  }
}
</script>

<style>
  .todo {
    color: gray;
    text-decoration: line-through;
  }
</style>
```



### 17. 리스트에서 삭제 버튼 구현 + 배열에서 인덱스로 삭제하기

```vue
<template>
  <div class="container">
    <h2>To-Do List</h2>

    <form @submit.prevent="onSubmit">
      <div class="d-flex">
        <div class="flex-grow-1 mr-2">
          <input
            class="form-control"
            type="text" 
            v-model="todo"
            placeholder="Type new to-do"
          >
        </div>
        <div>
          <button 
            class="btn btn-primary"
            type="submit"
          >
            Add
          </button>
        </div>
      </div>
      <div v-show="hasError" style="color: red">
        This field cannot be empty
      </div>
    </form>
    <div v-if="!todos.length">추가된 Todo가 없습니다.</div>
    <div 
      v-for="(todo, index) in todos"
      :key="todo.id"
      class="card mt-2"
    >    
      <div class="card-body p-2 d-flex align-items-center">
        <div class="form-check flex-grow-1">
          <input class="type-check-input" type="checkbox" v-model="todo.completed">
          <label class="form-check-label" :class="{todo: todo.completed}">
          {{ todo.subject }}
        </label>
        </div>
        <div>
          <button class="btn btn-danger btn-sm" @click="deleteTodo(index)">Delete</button>
      </div>
      </div>
    </div>
  </div>
</template>

<script>
import { ref } from 'vue';

export default {
  setup() {
    const todo = ref('');
    const todos = ref([]);
    const hasError = ref(false);
    const todoStyle = {
      textDecoration: 'line-through',
      color: 'gray'
    };

    const onSubmit = () => {
      if (todo.value === '') {
        hasError.value = true;
      } else {
        todos.value.push({
          id: Date.now(),
          subject: todo.value,
          completed: false,
        });
        hasError.value = false;
        todo.value = '';
      }
    };

    const deleteTodo = (index) => {
      todos.value.splice(index, 1);
    }

    return {
      todo,
      todos,
      onSubmit,
      hasError,
      todoStyle,
      deleteTodo,
    };
  }
}
</script>

<style>
  .todo {
    color: gray;
    text-decoration: line-through;
  }
</style>
```



### 폼 컨포넌트로 빼기

- `front\src\components\TodoSimpleForm.vue `뷰 생성하기
- 컴포넌트 등록하기

```vue
<script>
import { ref } from 'vue';
import TodoSimpleForm from './components/TodoSimpleForm.vue';

export default {
  components: {
    TodoSimpleForm
  },
```



### 폼 컴포넌트로 뺀 거 실행되도록 스크립트 빼기

- 자식 컴포넌트에서 부모 컴포넌트로 emit 을 통해 데이터를 보내는 방법입니다.

`app.vue`

```vue
<template>
  <div class="container">
    <h2>To-Do List</h2>
    <TodoSimpleForm @add-todo="addTodo" />
    <div v-if="!todos.length">추가된 Todo가 없습니다.</div>
    <div 
      v-for="(todo, index) in todos"
      :key="todo.id"
      class="card mt-2"
    >    
      <div class="card-body p-2 d-flex align-items-center">
        <div class="form-check flex-grow-1">
          <input class="type-check-input" type="checkbox" v-model="todo.completed">
          <label class="form-check-label" :class="{todo: todo.completed}">
          {{ todo.subject }}
        </label>
        </div>
        <div>
          <button class="btn btn-danger btn-sm" @click="deleteTodo(index)">Delete</button>
      </div>
      </div>
    </div>
  </div>
</template>

<script>
import { ref } from 'vue';
import TodoSimpleForm from './components/TodoSimpleForm.vue';

export default {
  components: {
    TodoSimpleForm
  },

  setup() {
    const todos = ref([]);

    const addTodo = (todo) => {
      console.log(todo);
      todos.value.push(todo);
    };
    const deleteTodo = (index) => {
        todos.value.splice(index, 1);
      }


    return {
      todos,
      addTodo,
      deleteTodo,
    };
  }
}
</script>

<style>
  .todo {
    color: gray;
    text-decoration: line-through;
  }
</style>

```



`TodoSimpleForm.vue`

```vue
<template>
    <form @submit.prevent="onSubmit">
      <div class="d-flex">
        <div class="flex-grow-1 mr-2">
          <input
            class="form-control"
            type="text" 
            v-model="todo"
            placeholder="Type new to-do"
          >
        </div>
        <div>
          <button 
            class="btn btn-primary"
            type="submit"
          >
            Add
          </button>
        </div>
      </div>
      <div v-show="hasError" style="color: red">
        This field cannot be empty
      </div>
    </form>
</template>

<script>
    import { ref } from 'vue';

    export default {
        setup(props, context) {
        const todo = ref('');
        const hasError = ref(false);

        const onSubmit = () => {
            if (todo.value === '') {
                hasError.value = true;
            } else {
                context.emit('add-todo', {
                    id: Date.now(),
                    subject: todo.value,
                    completed: false,
                });
                hasError.value = false;
                todo.value = '';
            }
        };

        return {
            todo,
            hasError,
            onSubmit,
        }

    
    }

}
</script>

<style>
</style>
```
