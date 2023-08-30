<template>
  <q-page class="q-pa-xl">
    <section class="q-mb-xl">
      <div class="text-h4">QForm Component</div>
      <q-separator class="q-my-md" />
      <q-form
        ref="myForm"
        class="q-gutter-y-md q-mt-lg"
        autofocus
        greedy
        @submit="onSubmit"
        @reset="onReset"
      >
        <q-input
          outlined
          v-model="form.title"
          label="제목"
          :rules="[val => !!val || '필수 항목입니다']"
        />
        <q-input
          outlined
          v-model="form.content"
          label="내용"
          type="textarea"
          hint="50자 이내로 입력해주세요"
          counter
          :rules="[
            val => !!val || '필수 항목입니다',
            val => val.length <= 50 || '최대 50자 이내로 입력하세요',
          ]"
          lazy-rules
        />
        <q-select
          outlined
          v-model="form.tags"
          :options="tagOptions"
          label="태그"
          emit-value
          multiple
          hint="최대 2개 선택가능합니다"
          :rules="[
            val => val.length > 0 || '필수 항목입니다',
            val => val.length <= 2 || '최대 2개 까지 선택 가능합니다',
          ]"
        />
        <q-input outlined v-model="form.date" mask="date" :rules="['date']">
          <template v-slot:append>
            <q-icon name="event" class="cursor-pointer">
              <q-popup-proxy
                cover
                transition-show="scale"
                transition-hide="scale"
              >
                <q-date v-model="form.date">
                  <div class="row items-center justify-end">
                    <q-btn v-close-popup label="Close" color="primary" flat />
                  </div>
                </q-date>
              </q-popup-proxy>
            </q-icon>
          </template>
        </q-input>
        <q-toggle :label="`동의 하시겠습니까?`" v-model="form.accept" />
        <!-- false-value="Disagreed"
          true-value="Agreed" -->
        <div class="q-gutter-x-sm">
          <q-btn label="validate" color="secondary" @click="validate" />
          <q-btn label="resetValidation" color="warning" @click="reset" />
          <q-btn label="Submit" type="submit" color="primary" />
          <q-btn label="Reset" type="reset" color="primary" flat />
        </div>
      </q-form>
    </section>
  </q-page>
</template>

<script setup>
import { ref } from 'vue';
import { useQuasar } from 'quasar';

const $q = useQuasar();

const myForm = ref(null);
const form = ref({
  title: '',
  content: '',
  tags: [],
  date: '2022/01/01',
  accept: false,
});
// const tagOptions = ref(['Google', 'Facebook', 'Twitter', 'Apple', 'Oracle']);
const tagOptions = ref([
  { label: '구글', value: 'google' },
  { label: '페이스북', value: 'facebook' },
  { label: '트위터', value: 'twitter' },
  { label: '애플', value: 'apple' },
  { label: '오라클', value: 'oracle' },
]);
const validate = () => {
  myForm.value.validate().then(success => {
    if (success) {
      alert('성공입니다!');
    } else {
      alert('실패입니다!');
    }
  });
};
const reset = () => {
  myForm.value.resetValidation();
};
const onSubmit = () => {
  if (form.value.accept !== true) {
    alert('동의 해주세요!!!');
    return;
  }
  $q.loading.show();
  setTimeout(() => {
    $q.loading.hide();
    alert('성공~!');
  }, 3000);
  // alert('성공~!');
};
const onReset = () => {
  form.value.title = '';
  form.value.content = '';
  form.value.tags = [];
  form.value.date = '2022/01/01';
  form.value.accept = false;
};
</script>

<style lang="scss" scoped></style>
