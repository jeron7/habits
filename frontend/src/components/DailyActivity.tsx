import clsx from 'clsx'
import * as Checkbox from '@radix-ui/react-checkbox'
import * as Popover from '@radix-ui/react-popover'
import { CheckIcon, Cross2Icon } from '@radix-ui/react-icons'
import dayjs from 'dayjs'

import { Activity } from '../hooks/summarizeDaysActivities'

interface DailyActivityProps {
    activity: Activity;
}

interface HabitCheckBoxProps {
    id: string;
    title: string;
    checked: boolean;
}

function HabitCheckBox({ id, title, checked } : HabitCheckBoxProps) {
    return(
        <div key={id} className="flex flex-row gap-3 items-center">
            <Checkbox.Root
                id={id}
                className="flex flex-row gap-3 items-center group"
                checked={checked}
                onCheckedChange={() => console.log(`toggle habit with id ${id}`)}
            >
                <div className="w-8 h-8 rounded-lg bg-zinc-900 border-2 border-zinc-800 flex justify-center items-center group-data-[state=checked]:bg-green-500 group-data-[state=checked]:border-0">
                    <Checkbox.Indicator>
                        <CheckIcon className="w-5 h-5 text-white" />
                    </Checkbox.Indicator>
                </div>
            </Checkbox.Root>
            <label className="font-inter text-white" htmlFor={id}>{title}</label>
        </div>
    )
}

export function DailyActivity({ activity } : DailyActivityProps) {

    const { createdAt, practicedHabits, notPracticedHabits } = activity
    const createdAtDayJs = dayjs(createdAt)
    
    const totalPracticedHabits = practicedHabits.length
    const totalDayHabits = totalPracticedHabits + notPracticedHabits.length
    
    const completedPercentage =
        totalDayHabits > 0 ? Math.round((totalPracticedHabits / totalDayHabits) * 100) : 0

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
          <h3 className='font-inter font-semibold text-xs text-zinc-400'>{createdAtDayJs.format('dddd')}</h3>
          <h1 className='font-inter text-3xl text-white font-extrabold mt-2'>{createdAtDayJs.format('DD/MM')}</h1>
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
            {practicedHabits.map((habit, index) => (
                <HabitCheckBox key={index} id={habit.id} title={habit.title} checked={true} />
            ))}
            {notPracticedHabits.map((habit, index) => (
                <HabitCheckBox key={index} id={habit.id} title={habit.title} checked={false} />
            ))}
          </div>
          
        </Popover.Content>
      </Popover.Portal>
    </Popover.Root>
    )
}