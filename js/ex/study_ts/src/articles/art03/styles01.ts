import {Theme} from '@material-ui/core'
import { CSSProperties } from '@material-ui/core/styles/withStyles'

export const styles: (theme: Theme) => Record<string, CSSProperties> = (theme) => ({
    card: {
        ...theme.mixins.flexColumn,
        width: '100%',
        height: 'auto',
    } as CSSProperties,
    button: {
        margin: theme.spacing.unit,
    } as CSSProperties,
})
export type StyleProps = 'card' | 'button'
