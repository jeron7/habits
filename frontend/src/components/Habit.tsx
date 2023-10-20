interface HabitProps {
    completed: number
}

export function Habit(props : HabitProps) {
    return (
        <button 
            className="bg-violet-700 w-10 h-10 rounded flex items-center justify-center"
        >
            {props.completed}
        </button>
    )
}