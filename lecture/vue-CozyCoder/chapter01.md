# vue 기초1



### 1. 스크립트 태그로 변수 사용하기

- `<script>` 태그 안에 `export default`의 괄호 안에 아래와 같이 작성합니다.
- `<template>` 태그 안에는 반환하는 변수명을 `{{ }}` 안에 넣으면 출력됩니다. 

```vue
<template>
  <div>{{ name }}</div>
</template>

<script>
export default {
  setup() {
    const name = 'Kossie Coder';

    return {
      name
    }
  }
}
</script>

<style>

</style>
```



### 2. 스타일 적용하기

- `<style>` 태그 안에 `<template>` 태그 안의 태그 속성명으로 스타일 적용을 합니다.

```vue
<template>
  <div class="name">{{ name }}</div>
</template>

<script>
export default {
  setup() {
    const name = 'Kossie Coder';

    return {
      name
    }
  }
}
</script>

<style>
  .name {
    color: red;
  }
</style>
```



### 3. vue3의 `<div>` 태그 자동 합치기

- vue2에서는 여러 개의 엘리먼트를 나타내기 위해 하나의 `<div>` 태그 안에 작성해야 했지만, vue3 에서는 자동으로 처리해줍니다. 
  따라서 스타일 적용에 더욱 쉽게 이용할 수 있습니다.

```vue
<template>
  <div class="name">{{ name }}</div>
  <div>Hi</div>
</template>
```

<img src="C:\Users\piay8\AppData\Roaming\Typora\typora-user-images\image-20230817104349813.png" alt="image-20230817104349813" style="zoom:67%;" />



### 4. 함수로 출력하기1

- 일반적으로 화살표 함수를 사용합니다. 템플릿 태그 안에서는 반드시 함수명 뒤에 괄호를 함께 작성합니다.

```vue
<template>
  <div class="name">
    {{ greeting() }}
  </div>
  
</template>

<script>
export default {
  setup() {
    const name = 'Kossie Coder';

    const greeting = () => {
      return 'Hello';
    };
    
    return {
      name,
      greeting,
    }
  }
}
</script>

<style>
  .name {
    color: red;
  }
</style>
```



### 5. 함수로 출력하기2

- 변수를 사용하기 위해 아래의 규칙을 알아야 합니다.
  1. 함수의 괄호 안에 사용할 매개변수명을 작성한다.
  2. 템플릿 태그 안에 인자로 매개변수를 작성한다.

```vue
<template>
  <div class="name">
    {{ greeting(name) }}
  </div>
  
</template>

<script>
export default {
  setup() {
    const name = 'Kossie Coder';

    const greeting = (name) => {
      return 'Hello, ' + name;
    };
    
    return {
      name,
      greeting,
    }
  }
}
</script>

<style>
  .name {
    color: red;
  }
</style>
```



### 6. 함수로 출력하기3

- 여러 개의 함수를 사용할 때에는 아래와 같이 setup() 함수 내에서 다음과 같이 함수를 변수에 저장하고 사용합니다.

```vue
<template>
  <div class="name">
    {{ greet }}
  </div>
</template>

<script>
export default {
  setup() {
    const name = 'Kossie Coder';

    const greeting = (name) => {
      return 'Hello, ' + name;
    };

    const greet = greeting(name);
    
    return {
      greet
    }
  }
}
</script>

<style>
  .name {
    color: red;
  }
</style>
```



### 7. `import { ref } from 'vue';`

- 아래에 버튼을 클릭하면 console 창에서 이름이 변경되는 것을 확인할 수 있지만, 브라우저에서는 변경이 안됩니다.
  이 문제를 해결하기 위해 ref 를 사용해야 합니다.

```vue
<template>
  <div class="name">
    {{ name }}  <!-- error -->
  </div>
  <button 
    class="btn btn-primary"
    v-on:click="updateName"
  >
  Click
</button>
</template>

<script>


export default {
  setup() {
    let name = 'Kossie Coder1';
      
    const updateName = () => {
      name = 'Kossie Coder';
      console.log(name);
    };
    
    return {
      name,
      updateName,
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



아래와 같이 ref 를 임포트하고, 변경하는 변수 타입은 const 로 변경 후, ref 로 감싸줍니다. 그리고 변경하는 값은 변수명 뒤에 반드시 value 를 추가합니다. 문자열 뿐만 아니라 숫자, 오브젝트도 가능합니다.

```vue
<template>
  <div class="name">
    {{ name }}
  </div>
  <button 
    class="btn btn-primary"
    v-on:click="updateName"
  >
  Click
</button>
</template>

<script>

import { ref } from 'vue';

export default {
  setup() {
    const name = ref('Kossie Coder1');

    const updateName = () => {
      name.value = 'Kossie Coder';
      console.log(name);
    };
    
    return {
      name,
      updateName,
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



### 8. 브라우저에서 숫자 변경하기

- 문자열 변경할 때 ref 를 사용했지만, 숫자는 reactive 를 임포트해서 사용합니다.

```vue
<template>
  <div class="name">
    {{ name }}
    {{ name2 }}
  </div>
  <button 
    class="btn btn-primary"
    v-on:click="updateName"
  >
  Click
</button>
</template>

<script>

import { ref } from 'vue';
import { reactive } from 'vue';

export default {
  setup() {
    const name = ref('Kossie Coder1');
    const name2 = reactive({
      id: 1
    });

    const updateName = () => {
      name.value = 'Kossie Coder';
      name2.id = 2;
      console.log(name);
    };
    
    return {
      name,
      updateName,
      name2,
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



### 9. ` v-on: == @ / v-bind == :`

- 바인딩은 v-bind 으로 수행합니다. 하지만 `:` 으로 대체가 가능합니다.
- `v-on:` 은 `@` 으로 대체해서 사용이 가능합니다.

```vue
<template>
  <div :class="nameClass">
    {{ name }}
  </div>
  <input :type="type" :value="name">
  <button 
    class="btn btn-primary"
    @click="updateName"
  >
  Click
</button>
</template>

<script>

import { ref } from 'vue';

export default {
  setup() {
    const name = ref('Kossie');
    const type = ref('number');
    const nameClass = ref('name');

    const updateName = () => {
      name.value = 'Coder';
      type.value = 'text';
      nameClass.value = 'name';
    };
    
    return {
      name,
      type,
      updateName,
      nameClass,
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



### 10. 양방향 바인딩

- `v-model` 은 값 바인딩을 한 번에 처리해줍니다.

```vue
<template>
  <input 
    type="text" 
    v-model="name"
  >
  <button 
    class="btn btn-primary"
    @click="onSubmit"
  >
  Click
  </button>
</template>

<script>

import { ref } from 'vue';

export default {
  setup() {
    const name = ref('Kossie');
    
    const onSubmit = () => {
      console.log(name.value)
    };

    return {
      name,
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

