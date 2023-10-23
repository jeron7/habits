import clsx from 'clsx'
import * as Checkbox from '@radix-ui/react-checkbox'
import * as Popover from '@radix-ui/react-popover'
import { CheckIcon, Cross2Icon } from '@radix-ui/react-icons'

const activities = ['Atividade 1', 'Atividade 2', 'Atividade 3', 'Atividade 4', 'Atividade 5']

interface HabitProps {
    completed: number,
    amount?: number
}

export function Habit({ completed = 0, amount = 0 } : HabitProps) {

    const completedPercentage =
        amount > 0 ? Math.round((completed / amount) * 100) : 0

    return (
    <Popover.Root>
      <Popover.Trigger className='w-10 h-10' asChild>
        <button 
              className={
                  clsx("w-10 h-10 border-2 rounded-lg flex items-center justify-center",
                  {
                    "bg-violet-500 border-violet-300" : completedPercentage >= 80,
                    "bg-violet-600 border-violet-400" : completedPercentage < 80 && completedPercentage >= 60,
                    "bg-violet-700 border-violet-500" : completedPercentage < 60 && completedPercentage >= 40,
                    "bg-violet-800 border-violet-600" : completedPercentage < 40 && completedPercentage >= 20,
                    "bg-violet-900 border-violet-700" : completedPercentage < 20 && completedPercentage > 0,
                    "bg-zinc-900 border-zinc-800" : completedPercentage == 0
                  }
                )}          
          />
      </Popover.Trigger>
      <Popover.Portal>
        <Popover.Content className='w-[374px] h-fit bg-zinc-900 rounded-2xl p-6' side='right' sideOffset={0}>
          <Popover.Arrow width={8} height={8} fill='#18181b' accentHeight={5}/>
          <Popover.Close className='text-zinc-400 float-right'>
              <Cross2Icon className='w-6 h-6' aria-label='Fechar'/>
          </Popover.Close>
          <h3 className='font-inter font-semibold text-xs text-zinc-400'>Terça-feira</h3>
          <h1 className='font-inter text-3xl text-white font-extrabold mt-2'>03/01</h1>
          <div className='h-3 rounded-lg bg-zinc-700'>
            <span className={
                    clsx("h-3 mt-6 rounded-lg flex items-center justify-center",
                    {
                        "bg-violet-500 w-full" : completedPercentage >= 80,
                        "bg-violet-600 w-4/5" : completedPercentage < 80 && completedPercentage >= 60,
                        "bg-violet-700 w-3/5" : completedPercentage < 60 && completedPercentage >= 40,
                        "bg-violet-800 w-2/5" : completedPercentage < 40 && completedPercentage >= 20,
                        "bg-violet-900 w-1/5" : completedPercentage < 20 && completedPercentage > 0,
                        "" : completedPercentage == 0,
                    }
                  )} 
            />
          </div>
          
          <div className='flex flex-col gap-3 mt-6'>
            {/*TODO: Remove filter when proper fetching of daily habit is been done.*/}
            {activities.filter((_, index) => index + 1 <= completed).map((day, index) => {
                  return (
                      <div key={index} className="flex flex-row gap-3 items-center">
                          <Checkbox.Root
                              id={day}
                              className="flex flex-row gap-3 items-center group"
                              checked
                          >
                              <div className="w-8 h-8 rounded-lg bg-zinc-900 border-2 border-zinc-800 flex justify-center items-center group-data-[state=checked]:bg-green-500 group-data-[state=checked]:border-0">
                                  <Checkbox.Indicator>
                                      <CheckIcon className="w-5 h-5 text-white" />
                                  </Checkbox.Indicator>
                              </div>
                          </Checkbox.Root>
                          <label className="font-inter text-white" htmlFor={day}>{day}</label>
                      </div>
                  )
              })}
          </div>
          
        </Popover.Content>
      </Popover.Portal>
    </Popover.Root>
    )
}