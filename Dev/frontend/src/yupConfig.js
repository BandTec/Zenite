import { setLocale } from 'yup';

setLocale({
  string: {
    email: "Email deve ser válido",
  },
  mixed: {
    default: 'Campo inválido',
    required: "Campo obrigatório",
    notType: "Inválido"
  }
});
