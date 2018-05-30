import { h, Component } from 'preact';

const localTodos = JSON.parse(localStorage.getItem('todos') || '[]');

export default class TodoList extends Component {
    state = { todos: localTodos, text: '' };
    setText = e => {
        this.setState({ text: e.target.value });
    };
    addTodo = () => {
        let { todos, text } = this.state;
        todos = todos.concat({ text });
        localStorage.setItem('todos', JSON.stringify(todos));
        this.setState({ todos, text: '' });
    };
    render({ }, { todos, text }) {
        return (
            <form onSubmit={this.addTodo} action="javascript:">
                <input value={text} onInput={this.setText} />
                <button type="submit">Add</button>
                <ul>
                    { todos.map( todo => (
                        <li>{todo.text}</li>
                    )) }
                </ul>
            </form>
        );
    }
}
